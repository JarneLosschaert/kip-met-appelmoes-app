package be.howest.jarnelosschaert.kipmetappelmoes.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MapScreen(modifier: Modifier = Modifier) {
    Text(text = "map")
}

@Preview
@Composable
fun MapPreview() {
    MapScreen()
}