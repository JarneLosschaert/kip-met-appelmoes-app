package be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.profileTabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.howest.jarnelosschaert.kipmetappelmoes.R
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.TitleProfileTab

@Composable
fun SettingsScreen(modifier: Modifier = Modifier, onGoBack: () -> Unit) {
    Column(
        modifier = modifier.padding(10.dp)
    ) {
        TitleProfileTab(title = stringResource(R.string.title_settings), onGoBack = onGoBack)
    }
}

@Preview
@Composable
fun SettingsPreview() {
    SettingsScreen(onGoBack = {})
}