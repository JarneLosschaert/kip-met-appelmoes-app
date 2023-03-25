package be.howest.jarnelosschaert.kipmetappelmoes.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.howest.jarnelosschaert.kipmetappelmoes.R
import be.howest.jarnelosschaert.kipmetappelmoes.data.DataSource.restaurants
import be.howest.jarnelosschaert.kipmetappelmoes.data.Restaurant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import be.howest.jarnelosschaert.kipmetappelmoes.data.ChildFriendliness
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode


@Composable
fun SearchScreen(modifier : Modifier = Modifier) {
    val tags = enumValues<ChildFriendliness>().toList().map { it.name.lowercase(Locale.ROOT) }

    Column(modifier = modifier
        .padding(horizontal = 10.dp)
        .fillMaxWidth()) {
        var query by remember { mutableStateOf("") }
        SearchBar(
            query = query,
            onQueryChanged = {searchQuery -> query = searchQuery},
            onSearch = {}
        )
        Tags(tags = tags)
        Spacer(modifier = Modifier.height(10.dp))
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn {
            item {
                for (restaurant in restaurants) {
                    RestaurantCard(restaurant)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
fun Tags(tags: List<String>) {
    Spacer(modifier = Modifier.height(10.dp))
    var showMore by remember { mutableStateOf(false) }
    when {
        showMore -> {
            TagList(tags = tags, all = true)
        }
        else -> {
            TagList(tags = tags, all = false)
        }
    }
    ExpandIcon(
        modifier = Modifier.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null
    ) { showMore = !showMore }, showMore= showMore)
    Divider( color = MaterialTheme.colors.primary, thickness = 2.dp)
}

@Composable
fun ExpandIcon(modifier: Modifier = Modifier, showMore : Boolean) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(
                id = if (showMore) R.drawable.less else R.drawable.more),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
            modifier = modifier
                .width(50.dp)
        )
    }
}

@Composable
fun TagList(tags: List<String>, all : Boolean = true) {
    FlowRow(
        mainAxisSize = SizeMode.Expand,
        crossAxisSpacing = 5.dp,
        mainAxisSpacing = 8.dp,
    ) {
        var max = tags.size
        if (!all) {
            max = 6
        }
        for (i in 0 until max) {
            Text(
                text = tags[i],
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.secondary,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(8.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = MaterialTheme.colors.onSecondary
            )
        }
    }
}

@Composable
fun Tag(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier
            .background(MaterialTheme.colors.secondary, RoundedCornerShape(4.dp))
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
    )
}


@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: () -> Unit
) {
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .border(2.dp, MaterialTheme.colors.primary, RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChanged,
            placeholder = { Text("Search") },
            modifier = Modifier
                .weight(1f)
                .height(53.dp)
                .padding(end = 16.dp),
            singleLine = true,
            maxLines = 1,
            textStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                textColor = MaterialTheme.colors.onSurface
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearch() }
            )
        )
        Button(
            onClick = { /* handle button click */ },
            modifier = Modifier
                .height(53.dp)
                .width(50.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.sort),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
    }
}


@Composable
fun RestaurantCard(restaurant: Restaurant) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = restaurant.images[0]),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(160.dp, 150.dp)
                .padding(end = 10.dp),
        )
        Column {
            Text(
                restaurant.name,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                fontSize = 20.sp
            )
            StarsAndHart(restaurant = restaurant,
                modifier = Modifier.weight(1f))
            Text(getTodayOpeningHours(restaurant),
                modifier = Modifier.weight(1f))
            Text("4km",
                modifier = Modifier.weight(1f))
            Text(getEatingOptions(restaurant),
                modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun StarsAndHart(restaurant: Restaurant, modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            val averageRating = calculateAverageRating(restaurant)
            for (i in 1..5) {
                if (i <= averageRating) {
                    Star(image = R.drawable.fullstar)
                } else {
                    Star(image = R.drawable.emptystar)
                }
            }
        }
        Image(
            painter = painterResource(R.drawable.fullhart),
            contentDescription = null,
            modifier = Modifier
                .width(25.dp)
                .padding(end = 10.dp),
        )
    }
}

@Composable
fun Star(image: Int) {
    Image(
        painter = painterResource(image),
        contentDescription = null,
        modifier = Modifier
            .width(25.dp)
            .padding(end = 10.dp)
    )
}

fun calculateAverageRating(restaurant: Restaurant): Int {
    var total = 0
    for (review in restaurant.reviews) {
        total += review.rating
    }
    return (total.toFloat() / restaurant.reviews.size).roundToInt()
}

fun getTodayOpeningHours(restaurant: Restaurant): String {
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("EEEE")
    val currentDay = currentDateTime.format(formatter)
    return restaurant.openingHours[currentDay] ?: "Closed"
}

fun getEatingOptions(Restaurant: Restaurant): String {
    var eatingOptions = "~"
    for (eatingOption in Restaurant.eatingOptions) {
        val eatingOptionString = eatingOption.name.lowercase(Locale.getDefault())
        eatingOptions += "$eatingOptionString~"
    }
    return eatingOptions
}


@Preview
@Composable
fun SearchPreview() {
    SearchScreen()
}