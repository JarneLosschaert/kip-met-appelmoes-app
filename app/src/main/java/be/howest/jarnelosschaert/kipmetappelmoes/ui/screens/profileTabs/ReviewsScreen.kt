package be.howest.jarnelosschaert.kipmetappelmoes.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.TitleProfileTab

@Composable
fun ReviewsScreen(modifier: Modifier = Modifier, onGoBack: () -> Unit = {}) {
    Column(modifier = modifier.padding(horizontal = 10.dp)) {
        TitleProfileTab(title = "Beoordelingen", onGoBack = onGoBack)
    }
}