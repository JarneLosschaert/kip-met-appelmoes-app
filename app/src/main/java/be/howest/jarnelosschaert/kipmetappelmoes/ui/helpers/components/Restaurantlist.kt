package be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.howest.jarnelosschaert.kipmetappelmoes.R
import be.howest.jarnelosschaert.kipmetappelmoes.data.DummyData
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Restaurant
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.calculateAverageRating
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.getSortedEatingOptions
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.getTodayOpeningHours
import be.howest.jarnelosschaert.kipmetappelmoes.uiState
import coil.compose.rememberAsyncImagePainter

@Composable
fun RestaurantList(restaurants: List<Restaurant>, onRestaurantClicked : (Restaurant) -> Unit) {
    LazyColumn {
        items(restaurants.size) { index ->
            RestaurantCard(restaurant = restaurants[index], onRestaurantClicked = onRestaurantClicked)
        }
    }
}

@Composable
fun RestaurantCard(restaurant: Restaurant, onRestaurantClicked : (Restaurant) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable(onClick = { onRestaurantClicked(restaurant) }),
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = restaurant.images[0]),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(160.dp, 150.dp)
                .padding(end = 10.dp)
            )
        Column(modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween){
            Text(
                text = restaurant.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
            StarsAndHart(restaurant = restaurant,
                modifier = Modifier)
            Text(
                getTodayOpeningHours(restaurant),
                modifier = Modifier)
            Text(text = restaurant.address,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)
            EatingOptions(modifier = Modifier, restaurant)
        }
    }
    BasicSpacer()
}

@Composable
fun EatingOptions(modifier: Modifier, Restaurant: Restaurant) {
    val eatingOptions = getSortedEatingOptions(Restaurant.eatingOptions)
    Row(modifier = modifier) {
        Text(text = "~")
        for (eatingOption in eatingOptions) {
            val eatingOptionString = stringResource(id = eatingOption.value)
            Text(
                text = "$eatingOptionString~",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
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

            Stars(amount = averageRating)
        }
        if (uiState.currentUser.favorites.contains(restaurant.id)) {
            Icon(
                Icons.Default.Favorite,
                contentDescription = null,
                tint = androidx.compose.ui.graphics.Color(0xFFFF4033),
                modifier = Modifier
                    .width(25.dp)
                    .padding(end = 10.dp),
            )
        }
    }
}

@Composable
fun Stars(amount: Int) {
    for (i in 1..5) {
        if (i <= amount) {
            Star(image = R.drawable.fullchicken)
        } else {
            Star(image = R.drawable.emptychicken)
        }
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