package be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.howest.jarnelosschaert.kipmetappelmoes.R
import be.howest.jarnelosschaert.kipmetappelmoes.data.Restaurant
import be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.BasicSpacer
import coil.compose.rememberAsyncImagePainter

@Composable
fun RestaurantList(restaurants: List<Restaurant>) {
    LazyColumn {
        items(restaurants.size) { index ->
            RestaurantCard(restaurant = restaurants[index])
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
            Text(
                getTodayOpeningHours(restaurant),
                modifier = Modifier.weight(1f))
            Text("4km",
                modifier = Modifier.weight(1f))
            Text(
                getEatingOptions(restaurant),
                modifier = Modifier.weight(1f))
        }
    }
    BasicSpacer()
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