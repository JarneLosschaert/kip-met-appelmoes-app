package be.howest.jarnelosschaert.kipmetappelmoes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import be.howest.jarnelosschaert.kipmetappelmoes.R
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.TitleProfileTab
import be.howest.jarnelosschaert.kipmetappelmoes.uiState

@Composable
fun ReviewsScreen(modifier: Modifier = Modifier, onGoBack: () -> Unit = {}) {
    Column(modifier = modifier.padding(horizontal = 10.dp)) {
        TitleProfileTab(title = stringResource(R.string.title_ratings), onGoBack = onGoBack)
        if (uiState.currentUser.id == -1) {
            stringResource(R.string.loginfirst, stringResource(R.string.ratings))
        }
    }
}