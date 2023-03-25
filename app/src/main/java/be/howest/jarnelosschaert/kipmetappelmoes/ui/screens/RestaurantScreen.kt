package be.howest.jarnelosschaert.kipmetappelmoes.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.howest.jarnelosschaert.kipmetappelmoes.R
import be.howest.jarnelosschaert.kipmetappelmoes.data.DataSource.restaurants
import be.howest.jarnelosschaert.kipmetappelmoes.data.Restaurant
import coil.compose.rememberAsyncImagePainter
import java.time.LocalDate
import java.time.temporal.ChronoUnit


@Composable
fun RestaurantScreen(modifier: Modifier = Modifier) {
    val restaurant = restaurants[0]
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Images(restaurant = restaurant)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = restaurant.name,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.primaryVariant
            )
            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Description(restaurant)
                Info(restaurant)
                EatChoices(restaurant)
                ChildFriendliness(restaurant = restaurant)
                Reviews(restaurant = restaurant)
            }
        }
    }
}

@Composable
fun Images(restaurant: Restaurant){
    val images = restaurant.images
    LazyRow {
        items(images.size) { index ->
            Image(
                painter = rememberAsyncImagePainter(model = images[index]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(LocalConfiguration.current.screenWidthDp.dp)
                    .height(250.dp)
            )
        }
    }
}

@Composable
fun Description(restaurant: Restaurant) {
    SubTitle(text = "Beschrijving")
    Text(
        text = restaurant.description,
        fontSize = 16.sp,
    )
}

@Composable
fun Info(restaurant: Restaurant) {
    SubTitle(text = "Info")
    Row {
        Text(
            text = "Adres: ",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = restaurant.address,
            fontSize = 16.sp,
        )
    }
    Row {
        Text(
            text = "Telefoon: ",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = restaurant.phone,
            fontSize = 16.sp,
        )
    }
    Row {
        Text(
            text = "Site: ",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = restaurant.website,
            fontSize = 16.sp,
            textDecoration = TextDecoration.Underline
        )
    }
    Text(
        text = "Openingstijden: ",
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
        Spacer(modifier = Modifier.width(30.dp))
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
fun EatChoices(restaurant: Restaurant) {
    SubTitle(text = "Eetkeuzes")
    Row() {
        for (eatChoice in restaurant.eatingOptions) {
            Tag(
                text = eatChoice.name.lowercase()
            )
        }
    }
}

@Composable
fun Reviews(restaurant: Restaurant) {
    SubTitle(text = "Reviews")
    for (review in restaurant.reviews) {
        if (restaurant.reviews.indexOf(review) != 0) {
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = MaterialTheme.colors.primary, thickness = 1.dp)
            Spacer(modifier = Modifier.height(10.dp))
        }
        Text(
            text = review.user.firstName + " " + review.user.lastName,
            fontWeight = FontWeight.Bold
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 1..5) {
                if (i <= review.rating) {
                    Star(image = R.drawable.fullstar)
                } else {
                    Star(image = R.drawable.emptystar)
                }
            }
            Text(text = timeAgo(review.date))
        }
        Text(text = review.content)
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun ChildFriendliness(restaurant: Restaurant) {
    SubTitle(text = "Kindvriendelijkheid")
    Row() {
        for (eatChoice in restaurant.childFriendliness) {
            Tag(
                text = eatChoice.name.lowercase()
            )
        }
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
    Spacer(modifier = Modifier.height(6.dp))
}

fun timeAgo(date: LocalDate): String {
    val now = LocalDate.now()
    val years = ChronoUnit.YEARS.between(date, now)
    val months = ChronoUnit.MONTHS.between(date.withDayOfMonth(1), now.withDayOfMonth(1))
    val days = ChronoUnit.DAYS.between(date, now)

    return when {
        years > 0 -> "($years ${if (years == 1L) "year" else "years"} ago)"
        months > 0 -> "($months ${if (months == 1L) "month" else "months"} ago)"
        else -> "($days ${if (days == 1L) "day" else "days"} ago)"
    }
}

@Preview
@Composable
fun RestaurantPreview(){
    RestaurantScreen()
}
