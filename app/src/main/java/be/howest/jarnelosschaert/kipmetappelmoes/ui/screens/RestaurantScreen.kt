package be.howest.jarnelosschaert.kipmetappelmoes.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.howest.jarnelosschaert.kipmetappelmoes.R
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Restaurant
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.Stars
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.TagList
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.timeAgo
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.calculateAverageRating
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.BasicSpacer
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.GeneralPopup
import be.howest.jarnelosschaert.kipmetappelmoes.uiState
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay

@Composable
fun RestaurantScreen(
    modifier: Modifier = Modifier,
    restaurant: Restaurant,
    onReviewSubmitted: () -> Unit,
    onGoBack: () -> Unit,
    onFavoriteClicked: (Restaurant) -> Unit,
    addReview: (Restaurant, String, Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        item {
            HeaderRestaurant(restaurant = restaurant, onGoBack = onGoBack, onFavoriteClicked = onFavoriteClicked)
            BasicSpacer()
            RestaurantTitle(restaurant = restaurant)
            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Description(restaurant)
                Info(restaurant)
                EatingOptions(restaurant)
                ChildFriendliness(restaurant = restaurant)
                Reviews(restaurant = restaurant, onReviewSubmitted = onReviewSubmitted, addReview = addReview)
            }
        }
    }
}

@Composable
fun RestaurantTitle(restaurant: Restaurant) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = restaurant.name,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.primaryVariant
        )

        BasicSpacer()
        Row() {
            val averageRating = calculateAverageRating(restaurant)
            Stars(amount = averageRating)
        }
    }
}

@Composable
fun HeaderRestaurant(restaurant: Restaurant, onGoBack: () -> Unit, onFavoriteClicked: (Restaurant) -> Unit) {
    Box() {
        ImagesSlideshow(restaurant = restaurant)
        BackButton(onGoBack = onGoBack, modifier = Modifier.align(Alignment.TopStart))
        Row(modifier = Modifier.align(Alignment.TopEnd)) {
            ShareButton(restaurant = restaurant)
            if (uiState.currentUser.id != -1) {
                FavoriteButton(restaurant = restaurant, onFavoriteClicked = onFavoriteClicked)
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImagesSlideshow(restaurant: Restaurant) {
    val images = restaurant.images
    Card {
        AutoSlidingCarousel(
            itemsCount = images.size,
            itemContent = { index ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(images[index])
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(250.dp)
                )
            }
        )
    }
}

@Composable
fun IndicatorDot(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    autoSlideDuration: Long = 3000,
    pagerState: PagerState = remember { PagerState() },
    itemsCount: Int,
    itemContent: @Composable (index: Int) -> Unit,
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    LaunchedEffect(pagerState.currentPage) {
        delay(autoSlideDuration)
        pagerState.animateScrollToPage((pagerState.currentPage + 1) % itemsCount)
    }

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        HorizontalPager(count = itemsCount, state = pagerState) { page ->
            itemContent(page)
        }
        Surface(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.BottomCenter),
            color = Color.Black.copy(alpha = 0.5f)
        ) {
            DotsIndicator(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                totalDots = itemsCount,
                selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage,
                dotSize = 8.dp
            )
        }
    }
}
@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = MaterialTheme.colors.primary,
    unSelectedColor: Color = Color.Gray,
    dotSize: Dp
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unSelectedColor,
                size = dotSize
            )
            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Composable
fun BackButton(onGoBack: () -> Unit, modifier: Modifier) {
    Image(painter = painterResource(id = R.drawable.back),
        contentDescription = "go back",
        modifier = modifier
            .padding(10.dp)
            .size(40.dp)
            .clickable { onGoBack() }
    )
}

@Composable
fun FavoriteButton(restaurant: Restaurant, onFavoriteClicked: (Restaurant) -> Unit) {
    var icon: Int by remember { mutableStateOf(R.drawable.emptyhart) }
    if (uiState.currentUser.favorites.contains(restaurant.id)) {
        icon = R.drawable.fullhart
    }
    Image(painter = painterResource(id = icon), contentDescription = "hart",
        modifier = Modifier
            .padding(10.dp)
            .size(40.dp)
            .clickable {
                onFavoriteClicked(restaurant)
                icon = if (icon == R.drawable.emptyhart) {
                    R.drawable.fullhart
                } else {
                    R.drawable.emptyhart
                }
            }
    )
}

@Composable
fun ShareButton(restaurant: Restaurant) {
    val context = LocalContext.current
    Image(painter = painterResource(id = R.drawable.share),
        contentDescription = "share",
        modifier = Modifier
            .padding(10.dp)
            .size(40.dp)
            .clickable {

                val shareText = restaurant.name
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, shareText)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                context.startActivity(shareIntent)
            }
    )
}

@Composable
fun Description(restaurant: Restaurant) {
    SubTitle(text = stringResource(R.string.title_description))
    Text(
        text = restaurant.description,
        fontSize = 16.sp,
    )
}

@Composable
fun Info(restaurant: Restaurant) {
    SubTitle(text = stringResource(R.string.title_info))
    Row {
        Text(
            text = stringResource(R.string.title_address),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        BasicSpacer(width = 5)
        Text(
            text = restaurant.address + ", " + restaurant.city + " " + restaurant.postalCode,
            fontSize = 16.sp,
        )
    }
    Row {
        Text(
            text = stringResource(R.string.title_phone),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        BasicSpacer(width = 5)
        Text(
            text = restaurant.phone,
            fontSize = 16.sp,
        )
    }
    Row {
        Text(
            text = stringResource(R.string.title_site),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        BasicSpacer(width = 5)
        val url = restaurant.website
        Link(text = url)
    }
    Text(
        text = stringResource(R.string.title_opening_hours),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
    )
    Row {
        Column() {
            restaurant.openingHours.keys.forEach { day ->
                Text(
                    text = day,
                    fontSize = 16.sp,
                )
            }
        }
        BasicSpacer(height = 30)
        Column() {
            restaurant.openingHours.values.forEach { hours ->
                Text(
                    text = hours,
                    fontSize = 16.sp,
                )
            }
        }
    }
}

@Composable
fun Link(text: String) {
    val uriHandler = LocalUriHandler.current
    ClickableText(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 16.sp
                )
            ) {
                append(text)
            }
        },
        onClick = {
            uriHandler.openUri(text)
        }
    )
}

@Composable
fun EatingOptions(restaurant: Restaurant) {
    SubTitle(text = stringResource(R.string.title_eating_options))
    TagList(tags = restaurant.eatingOptions.toList())
}

@Composable
fun ChildFriendliness(restaurant: Restaurant) {
    SubTitle(text = stringResource(R.string.title_childfriendliness))
    TagList(tags = restaurant.childFriendliness.toList())
}


@Composable
fun Reviews(restaurant: Restaurant, onReviewSubmitted: () -> Unit, addReview: (Restaurant, String, Int) -> Unit) {
    SubTitle(text = stringResource(R.string.title_reviews))
    for (review in restaurant.reviews) {
        if (restaurant.reviews.indexOf(review) != 0) {
            BasicSpacer()
            Divider(color = MaterialTheme.colors.primary, thickness = 1.dp)
            BasicSpacer()
        }
        Text(
            text = review.user.username,
            fontWeight = FontWeight.Bold
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Stars(amount = review.rating)
            Text(text = timeAgo(review.date))
        }
        Text(text = review.content)
    }
    BasicSpacer()
    if (uiState.currentUser.id != -1) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            ReviewPopup(restaurant = restaurant, onReviewSubmitted = onReviewSubmitted, addReview = addReview)
        }
    }
    BasicSpacer()
}

@Composable
fun ReviewPopup(restaurant: Restaurant, onReviewSubmitted: () -> Unit, addReview: (Restaurant, String, Int) -> Unit) {
    var rating by remember { mutableStateOf(0) }
    var content by remember { mutableStateOf("") }
    var isPopupVisible by remember { mutableStateOf(false) }
    var isReviewSubmitted by remember { mutableStateOf(false) }

    if (isPopupVisible) {
        AlertDialog(
            onDismissRequest = { isPopupVisible = false },
            title = { Text(stringResource(R.string.title_add_review)) },
            confirmButton = {
                Button(
                    onClick = {
                        addReview(restaurant, content, rating)
                        isPopupVisible = false
                        isReviewSubmitted = true
                    },
                    enabled = content.isNotEmpty()
                ) {
                    Text(stringResource(R.string.submit))
                }
            },
            dismissButton = {
                Button(
                    onClick = { isPopupVisible = false }
                ) {
                    Text(stringResource(R.string.cancel))
                }
            },
            text = {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(stringResource(R.string.rating))
                        BasicSpacer(width = 9)
                        RatingBar(
                            rating = rating.toFloat(),
                            onRatingChanged = { rating = it.toInt() }
                        )
                    }
                    BasicSpacer()
                    TextField(
                        value = content,
                        onValueChange = { content = it },
                        label = { Text(stringResource(R.string.review)) },
                        singleLine = true,
                        maxLines = Int.MAX_VALUE,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 200.dp)
                            .onFocusChanged { if (it.isFocused) isPopupVisible = true }
                    )
                }
            }
        )
    }
    Button(
        onClick = { isPopupVisible = true }
    ) {
        Text(stringResource(R.string.add_review))
    }

    if (isReviewSubmitted) {
        GeneralPopup(title = stringResource(R.string.title_reviews), content = stringResource(R.string.reviews_thanks), confirmButton = stringResource(
                    R.string.you_are_welcome), onDismiss = {
            isReviewSubmitted = false
            onReviewSubmitted()
        })
    }
}

@Composable
fun RatingBar(rating: Float, onRatingChanged: (Float) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(5) { index ->
            val isSelected = index <= rating - 1
            val starIcon = if (isSelected) Icons.Default.Star else Icons.Filled.Star
            val starColor = if (isSelected) MaterialTheme.colors.secondary else Color.Gray
            StarButton(starIcon, starColor) {
                onRatingChanged(index + 1f)
            }
        }
    }
}

@Composable
fun StarButton(icon: ImageVector, color: Color, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(20.dp)
            .padding(0.dp)
            .clip(CircleShape)
            .background(color)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun SubTitle(text: String) {
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.primary
    )
    BasicSpacer(height = 6)
}

@Preview
@Composable
fun RestaurantPreview() {
    //RestaurantScreen(restaurant = restaurants[0], onReviewSubmitted = { }, onGoBack = { }, )
}
