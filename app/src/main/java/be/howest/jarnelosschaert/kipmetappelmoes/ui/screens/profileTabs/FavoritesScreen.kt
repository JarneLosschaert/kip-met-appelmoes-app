package be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.profileTabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.howest.jarnelosschaert.kipmetappelmoes.R
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Restaurant
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.BasicSpacer
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.NothingFound
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.RestaurantList
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.TitleProfileTab
import be.howest.jarnelosschaert.kipmetappelmoes.uiState

@Composable
fun FavoritesScreen(modifier: Modifier = Modifier, favorites: List<Restaurant>, onRestaurantClicked: (Restaurant) -> Unit = {}, onGoBack: () -> Unit = {}) {
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleProfileTab(title = stringResource(R.string.title_favorites), onGoBack = onGoBack)
        BasicSpacer()
        if (uiState.currentUser.id == -1) {
            NothingFound(text = stringResource(R.string.loginfirst, stringResource(R.string.favorites)))
        } else {
            if (favorites.isEmpty()) {
                NothingFound(text = stringResource(R.string.no_favorites))
            } else {
                RestaurantList(restaurants = favorites, onRestaurantClicked = onRestaurantClicked)
            }
        }
    }
}

@Preview
@Composable
fun FavoritesPreview() {
    FavoritesScreen( favorites = listOf())
}