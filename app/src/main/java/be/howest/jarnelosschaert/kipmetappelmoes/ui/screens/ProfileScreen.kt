package be.howest.jarnelosschaert.kipmetappelmoes.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.BasicSpacer
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import be.howest.jarnelosschaert.kipmetappelmoes.R
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.User
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.GeneralChoicePopup
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.GeneralPopup
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.GeneralTextPopup
import be.howest.jarnelosschaert.kipmetappelmoes.uiState

sealed class ProfileTabs(open val name: Int, open val icon: ImageVector)

sealed class ProfileTabsScreen(
    override val name: Int,
    override val icon: ImageVector,
    val route: String
) : ProfileTabs(name, icon) {
    object Account : ProfileTabsScreen(R.string.tab_account, Icons.Default.AccountCircle, "account")
    object Reviews : ProfileTabsScreen(R.string.tab_reviews, Icons.Default.Star, "reviews")
    object Favorites : ProfileTabsScreen(R.string.tab_favorites, Icons.Default.Favorite, "favorites")
    object Settings : ProfileTabsScreen(R.string.tab_settings, Icons.Default.Settings, "settings")
}

sealed class ProfileTabsPopup(
    override val name: Int,
    override val icon: ImageVector,
    val popup: @Composable (() -> Unit) -> Unit
) : ProfileTabs(name, icon) {
    object Nothing : ProfileTabsPopup(0, Icons.Default.AccountCircle, {})
    object LogOut : ProfileTabsPopup(R.string.tab_logout, Icons.Default.ExitToApp, { onDismiss ->
        GeneralChoicePopup(
            title = "Uitloggen",
            content = "Ben je zeker dat je wilt uitloggen?",
            confirmButton = "Uitloggen",
            toastText = "Je bent succesvol uitgelogd!",
            onDismiss = { onDismiss() },
            onConfirm = {
                uiState.currentUser = User(-1, "", "", listOf())
            }
        )})
    object Help : ProfileTabsPopup(R.string.tab_help, Icons.Default.Call, { onDismiss ->
        GeneralTextPopup(
            title = "Help",
            label = "Heb je een vraag over de app? Laat het ons weten!",
            confirmButton = "Versturen",
            toastText = "Je vraag is succesvol verstuurd!",
            onDismiss = { onDismiss() }
        )})
    object Problem : ProfileTabsPopup(R.string.tab_problem, Icons.Default.Warning, { onDismiss ->
        GeneralTextPopup(
            title = "Probleem rapporteren",
            label = "Heb je een probleem met de app? Laat het ons weten!",
            confirmButton = "Versturen",
            toastText = "Je probleem is succesvol verstuurd!",
            onDismiss = { onDismiss() }
        )})
    object Info : ProfileTabsPopup(R.string.tab_info, Icons.Default.Info, { onDismiss ->
        GeneralPopup(
            title = "Info",
            content = "Kip met appelmoes is een app gemaakt door Jarne losschaert voor het Howest project 'Native Mobile Apps'.",
            confirmButton = "Ok",
            onDismiss = { onDismiss() },
        )})
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, onScreenChange: (String) -> Unit) {
    Column(modifier = modifier) {
        HeaderProfile()
        val tabs = listOf(
            ProfileTabsScreen.Account,
            ProfileTabsScreen.Reviews,
            ProfileTabsScreen.Favorites,
            ProfileTabsScreen.Settings,
            ProfileTabsPopup.Help,
            ProfileTabsPopup.Problem,
            ProfileTabsPopup.Info,
            ProfileTabsPopup.LogOut
            )
        Column(modifier = Modifier
            .padding(horizontal = 10.dp)) {
            for (tab in tabs) {
                TabItem(modifier = Modifier.weight(3f), tab = tab, onScreenChange = onScreenChange)
                Box(modifier = Modifier.weight(1f)) {
                    Spacer(modifier = Modifier.fillMaxHeight())
                }
            }
        }
    }
}

@Composable
fun HeaderProfile() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .height(150.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(Icons.Default.AccountCircle, "Profiel", modifier = Modifier.size(100.dp), tint = MaterialTheme.colors.primaryVariant)
        BasicSpacer()
        Text(text = uiState.currentUser.username, color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun TabItem(modifier: Modifier, tab: ProfileTabs, onScreenChange: (String) -> Unit) {
    var displayPopup by remember { mutableStateOf<ProfileTabsPopup>(ProfileTabsPopup.Nothing) }
    if (displayPopup !is ProfileTabsPopup.Nothing) {
        displayPopup.popup { displayPopup = ProfileTabsPopup.Nothing }
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable {
                if (tab is ProfileTabsScreen) {
                    onScreenChange(tab.route)
                }
                if (tab is ProfileTabsPopup) {
                    displayPopup = tab
                }
            },
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(tab.icon, null, tint = MaterialTheme.colors.primary, modifier = Modifier.size(30.dp))
            BasicSpacer()
            Text(text = stringResource(id = tab.name), color = MaterialTheme.colors.primaryVariant)
        }
        if (tab is ProfileTabsScreen) {
            Icon(Icons.Default.ArrowForward, "Naar", modifier = Modifier.align(Alignment.CenterEnd), tint = MaterialTheme.colors.primary)
        }
    }
}