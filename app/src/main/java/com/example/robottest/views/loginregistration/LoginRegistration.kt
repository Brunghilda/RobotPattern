package com.example.robottest

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.robottest.views.loginregistration.MainActivityViewModel
import kotlinx.coroutines.launch

/**
 * This composable shows Login view at the beginning and when the user clicks on
 * "Not yet registered? Register" Button below then Registration view will be displayed.
 * From the Registration view the user can go back to the Login view by clicking the link below.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginRegistration(goToHome: () -> Unit) {

    val viewModel: MainActivityViewModel = viewModel()
    val showLogin = remember { mutableStateOf(true) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()
    val openDialog = remember { mutableStateOf(false) }

    if (showLogin.value) {
        Login(goToRegister = {
            showLogin.value = false
        }, onLogin = { email, password ->
            keyboardController?.hide()
            coroutineScope.launch {
                val loggedIn = viewModel.login(email = email, password = password)
                if (loggedIn) {
                    goToHome()
                } else {
                    openDialog.value = true
                }
            }
        }, viewModel = viewModel)

        if (openDialog.value) {
            AlertDialog(onDismissRequest = { openDialog.value = false },
                title = {
                    Text(
                        text = stringResource(id = R.string.login_failed),
                        modifier = Modifier.semantics { contentDescription = "Login failed" })
                },
                confirmButton = {
                    Button(
                        onClick = { openDialog.value = false },
                        modifier = Modifier.semantics {
                            contentDescription = "Login Error Button"
                        }) {
                        Text(text = stringResource(id = R.string.try_again))
                    }
                }
            )
        }
    } else {
        Registration(goToLogin = {
            showLogin.value = true
        }, onRegister = {

        })
    }
}

@Composable
fun Login(
    goToRegister: () -> Unit,
    onLogin: (email: String, password: String) -> Unit,
    viewModel: MainActivityViewModel
) {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val loginState = viewModel.loginInProgress
    Column(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = stringResource(id = R.string.my_awesome_app_name),
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = "Login Title" }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            Modifier
                .fillMaxWidth()
                .semantics { contentDescription = "Login Email" },
            label = {
                Text(text = stringResource(id = R.string.email))
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            Modifier
                .fillMaxWidth()
                .semantics { contentDescription = "Login Password" },
            label = {
                Text(text = stringResource(id = R.string.password))
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                onLogin(emailState.value, passwordState.value)
            })
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            onClick = { onLogin(emailState.value, passwordState.value) },
            modifier = Modifier.fillMaxWidth()
        ) {
            if (loginState.value) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                )
            } else {
                Text(text = stringResource(id = R.string.login))
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        TextButton(modifier = Modifier.fillMaxWidth(),
            onClick = { goToRegister() }
        ) {
            Text(
                text = stringResource(id = R.string.not_yet_registered),
                style = TextStyle(fontSize = 14.sp, textAlign = TextAlign.Center)
            )
        }
    }
}

@Composable
fun Registration(goToLogin: () -> Unit, onRegister: () -> Unit) {
    val usernameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val passwordRepeatState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = stringResource(id = R.string.my_awesome_app_name),
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = usernameState.value,
            onValueChange = { usernameState.value = it },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = stringResource(id = R.string.username))
            }
        )
        OutlinedTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = stringResource(id = R.string.email))
            }
        )
        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = stringResource(id = R.string.password))
            }
        )
        OutlinedTextField(
            value = passwordRepeatState.value,
            onValueChange = { passwordRepeatState.value = it },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = stringResource(id = R.string.password_repeat))
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(onClick = { onRegister() }, modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(id = R.string.register))
        }
        Spacer(modifier = Modifier.height(32.dp))
        TextButton(modifier = Modifier.fillMaxWidth(),
            onClick = { goToLogin() }
        ) {
            Text(
                text = stringResource(id = R.string.already_have_an_account),
                style = TextStyle(fontSize = 14.sp, textAlign = TextAlign.Center)
            )
        }
    }
}