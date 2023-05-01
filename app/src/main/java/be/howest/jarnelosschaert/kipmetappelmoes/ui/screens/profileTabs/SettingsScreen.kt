package be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.profileTabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.TitleProfileTab

@Composable
fun SettingsScreen(modifier: Modifier = Modifier, onGoBack: () -> Unit) {
    Column(
        modifier = modifier.padding(10.dp)
    ) {
        TitleProfileTab(title = "Instellingen", onGoBack = onGoBack)
        Text(
            text = "General Settings",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        SwitchSetting(
            label = "Enable notifications",
            isChecked = true,
            onCheckedChange = { /* Handle switch state change */ }
        )
        Divider(
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Text(
            text = "Appearance",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        RadioButtonSetting(
            label = "Light theme",
            isSelected = true,
            onClick = { /* Handle radio button selection */ }
        )
        RadioButtonSetting(
            label = "Dark theme",
            isSelected = false,
            onClick = { /* Handle radio button selection */ }
        )
    }
}

@Composable
fun SwitchSetting(
    label: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
fun RadioButtonSetting(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick
        )
        Text(
            text = label,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}