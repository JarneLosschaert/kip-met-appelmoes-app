package be.howest.jarnelosschaert.kipmetappelmoes.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier

@Composable
fun FavoritesScreen(modifier: Modifier = Modifier) {
    Text(text = "Favorites")
}

@Preview
@Composable
fun FavoritesPreview() {
    FavoritesScreen()
}