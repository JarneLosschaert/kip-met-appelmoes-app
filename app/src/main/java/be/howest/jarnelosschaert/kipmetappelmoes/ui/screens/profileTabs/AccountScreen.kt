package be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.profileTabs

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.howest.jarnelosschaert.kipmetappelmoes.R
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.TitleProfileTab

@Composable
fun AccountScreen(modifier: Modifier = Modifier, onGoBack : () -> Unit = {}) {
    Column(modifier = modifier.padding(horizontal = 10.dp)) {
        TitleProfileTab(title = "Account", onGoBack = onGoBack)
    }
}