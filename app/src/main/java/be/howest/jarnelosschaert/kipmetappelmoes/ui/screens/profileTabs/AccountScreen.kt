package be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.profileTabs

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.howest.jarnelosschaert.kipmetappelmoes.R
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.User
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.BasicSpacer
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.TitleProfileTab
import be.howest.jarnelosschaert.kipmetappelmoes.uiState

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    onRegister: (String, String) -> Unit,
    onLogin: (String, String) -> Boolean,
    onLoginSuccess: (String) -> Unit,
    onGoBack: () -> Unit = {}
) {
    Column(modifier = modifier.padding(horizontal = 10.dp)) {
        TitleProfileTab(title = stringResource(R.string.title_account), onGoBack = onGoBack)
        if (uiState.currentUser.id == -1) {
            LoginPage(onRegister = onRegister, onLogin = onLogin, onLoginSuccess = onLoginSuccess)
        } else {
            ManageAccountScreen()
        }
    }
}

@Composable
fun ManageAccountScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicSpacer(height = 20)
        Icon(
            Icons.Default.AccountCircle,
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .padding(16.dp)
        )
        Text(uiState.currentUser.username)
    }

    Button(
        onClick = { uiState.currentUser = User(-1, "", "", listOf()) },
        modifier = Modifier
            .padding(top = 20.dp)
    ) {
        Text(stringResource(R.string.logout))
    }
}

@Composable
fun LoginPage(onRegister: (String, String) -> Unit,onLoginSuccess: (String) -> Unit, onLogin: (String, String) -> Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicSpacer(height = 50)
        Image(
            painter = painterResource(id = R.drawable.chicken),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .padding(16.dp)
        )

        var loginPopupVisible by remember { mutableStateOf(false) }
        var registerPopupVisible by remember { mutableStateOf(false) }

        Button(
            onClick = { loginPopupVisible = true },
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            Text(stringResource(R.string.login))
        }

        Button(
            onClick = { registerPopupVisible = true },
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            Text(stringResource(R.string.register))
        }

        var isError by remember { mutableStateOf(false) }
        val context = LocalContext.current
        val toastTextLogin = stringResource(R.string.succesfull_login)
        val toastTextRegister = stringResource(R.string.succesfull_register)

        if (loginPopupVisible) {
            LoginRegisterPopup(
                title = stringResource(R.string.popuptitle_login), submit = stringResource(R.string.login), isError = isError,
                onSubmit = { username, password ->
                    if (onLogin(username, password)) {
                        loginPopupVisible = false
                        isError = false
                        onLoginSuccess(username)
                        Toast.makeText(context, toastTextLogin, Toast.LENGTH_SHORT).show()
                    } else {
                        isError = true
                    }
                },
                onDismiss = { loginPopupVisible = false }
            )
        }
        if (registerPopupVisible) {
            LoginRegisterPopup(
                title = stringResource(R.string.popuptitle_register), submit = stringResource(R.string.register), isError = isError,
                onSubmit = { username, password ->
                    onRegister(username, password)
                    registerPopupVisible = false
                    Toast.makeText(context, toastTextRegister, Toast.LENGTH_SHORT).show()
                },
                onDismiss = {
                    registerPopupVisible = false
                }
            )
        }
    }
}

@Composable
fun LoginRegisterPopup(
    title: String,
    submit: String,
    isError: Boolean,
    onDismiss: () -> Unit,
    onSubmit: (String, String) -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(title) },
        confirmButton = {
            Button(
                onClick = {
                    onSubmit(username, password)
                },
                enabled = username.isNotEmpty() and password.isNotEmpty()
            ) {
                Text(submit)
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismiss() }
            ) {
                Text(stringResource(R.string.cancel))
            }
        },
        text = {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text(stringResource(R.string.username)) },
                    singleLine = true,
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 200.dp)
                )

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(stringResource(R.string.password)) },
                    singleLine = true,
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 200.dp)
                )
                if (isError) {
                    Text(
                        text = stringResource(R.string.failed_login),
                        color = Color.Red,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun AccountScreenPreview() {
    AccountScreen(
        onRegister = { _, _ -> },
        onLogin = { _, _ -> true },
        onLoginSuccess = { }
    )
}