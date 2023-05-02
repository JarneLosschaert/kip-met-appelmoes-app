package be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.profileTabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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