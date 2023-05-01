package be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components

import android.app.Activity
import android.icu.text.CaseMap.Title
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import be.howest.jarnelosschaert.kipmetappelmoes.R


@Composable
fun BasicSpacer(height: Int = 10, width: Int = 10) {
    Spacer(
        modifier = Modifier
            .height(height.dp)
            .width(width.dp)
    )
}
@Composable
fun TitleProfileTab(title: String, onGoBack: () -> Unit) {
    Column {
        Box(modifier = Modifier.fillMaxWidth()) {
            Icon(
                Icons.Default.ArrowBack, contentDescription = "back",
                modifier = Modifier.align(Alignment.BottomStart)
                    .clickable(onClick = { onGoBack() })
                    .size(30.dp)
                    .padding(start = 0.dp),
                tint = MaterialTheme.colors.primaryVariant
            )
            Text(
                text = title,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp)
                    .align(Alignment.Center),
                color = MaterialTheme.colors.primaryVariant,
            )
        }
        Divider(
            color = MaterialTheme.colors.primaryVariant,
            thickness = 2.dp,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Composable
fun GeneralPopup(
    title: String,
    content: String,
    confirmButton: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title) },
        text = {
            Text(text = content)
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                    onDismiss()
                }
            ) {
                Text(text = confirmButton)
            }
        }
    )
}

@Composable
fun GeneralChoicePopup(
    title: String,
    content: String,
    confirmButton: String,
    toastText: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit = {}
) {
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title) },
        text = {
            Text(text = content)
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                    onDismiss()
                    Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(text = confirmButton)
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismiss() },
                modifier = Modifier.padding(end = 10.dp)
            ) {
                Text(text = "Annuleren")
            }
        },
    )
}


@Composable
fun GeneralTextPopup(
    title: String,
    label: String,
    confirmButton: String,
    toastText: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit = {}
) {
    val context = LocalContext.current
    var problemText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title) },
        text = {
            Column {
                Text(text = label)
                TextField(
                    value = problemText,
                    onValueChange = { problemText = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                    onDismiss()
                    Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(text = confirmButton)
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismiss() },
                modifier = Modifier.padding(end = 10.dp)
            ) {
                Text(text = "Annuleren")
            }
        },
    )
}
