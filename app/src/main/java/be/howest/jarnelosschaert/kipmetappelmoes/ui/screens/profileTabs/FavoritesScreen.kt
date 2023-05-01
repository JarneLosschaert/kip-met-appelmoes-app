package be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.profileTabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.howest.jarnelosschaert.kipmetappelmoes.R
import be.howest.jarnelosschaert.kipmetappelmoes.controller
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.RestaurantList
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Restaurant
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.BasicSpacer
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.TitleProfileTab

@Composable
fun FavoritesScreen(modifier: Modifier = Modifier, onRestaurantClicked: (Restaurant) -> Unit = {}, onGoBack: () -> Unit = {}) {
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleProfileTab(title = "Favorieten", onGoBack = onGoBack)
        BasicSpacer()
       RestaurantList(restaurants = controller.getFavorites(), onRestaurantClicked = onRestaurantClicked)
    }
}

@Composable
fun RestaurantTitle() {
    Text(
        text = stringResource(R.string.title_favorites),
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 10.dp),
        color = MaterialTheme.colors.primaryVariant,
        textAlign = TextAlign.Center
    )

    Divider(
        color = MaterialTheme.colors.primaryVariant,
        thickness = 2.dp,
        modifier = Modifier.padding(top = 10.dp)
    )
}

@Preview
@Composable
fun FavoritesPreview() {
    FavoritesScreen()
}