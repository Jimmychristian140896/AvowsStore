package com.jimmy.avowsstore.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jimmy.avowsstore.R
import com.jimmy.avowsstore.core.composable.ObserveAsEvents
import com.jimmy.avowsstore.core.composable.ValidationTextField
import com.jimmy.avowsstore.core.ui.showToast
import com.jimmy.avowsstore.navigation.Route
import com.jimmy.avowsstore.presentation.productdetail.ProductDetailAction
import com.jimmy.avowsstore.presentation.profile.ProfileAction
import com.jimmy.avowsstore.ui.theme.Green
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel: LoginViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    ObserveAsEvents(viewModel.eventChannel) { event ->
        when (event) {
            is LoginEvent.OnLoginFailed -> {
                context.showToast(event.message.asString(context))

            }

            LoginEvent.OnLoginSuccess -> {
                context.showToast(context.getString(R.string.login_success))
                navHostController.navigate(Route.Main)
            }
        }
    }
    Box(
        modifier =
        modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        LoginScreen(state = state, onAction = viewModel::onAction)
        /*if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
            )
        }*/
    }
}

@Composable
fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
    modifier: Modifier = Modifier
) {
    /*Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(48.dp))
        Image(
            painter = painterResource(R.drawable.ic_avows_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .height(140.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(32.dp))

        ValidationTextField(
            state.username,
            onValueChange = {
                onAction(LoginAction.OnUsernameChanged(it))
            },
            hint = "Username",
            isValid = false,
            modifier = Modifier,
        )
        Spacer(Modifier.height(16.dp))
        ValidationTextField(
            state.password,
            onValueChange = {
                onAction(LoginAction.OnPasswordChanged(it))
            },
            hint = "Password",
            isValid = false,
            modifier = Modifier,
        )
        *//*val usernameState = rememberTextFieldState(state.username)
        LaunchedEffect(usernameState.text) {
            onAction(LoginAction.OnUsernameChanged(usernameState.text.toString()))
        }
        BasicTextField(
            state = usernameState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            decorator = { innerTextField ->
                if (state.username.isEmpty()) {
                    Text(text = "Username")
                }
                innerTextField()
            }
        )*//*
        *//*val passwordState = rememberTextFieldState(state.password)
        LaunchedEffect(passwordState.text) {
            onAction(LoginAction.OnPasswordChanged(passwordState.text.toString()))
        }
        BasicTextField(
            state = passwordState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            decorator = { innerTextField ->
                if (state.password.isEmpty()) {
                    Text(text = "Password")
                }
                innerTextField()
            }
        )*//*
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = {
                onAction(LoginAction.OnLoginClicked)
            },
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            //.padding(horizontal = 16.dp, vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green
            ),
            shape = RoundedCornerShape(8.dp)
        ) {

            Text(
                text = "Login",
                color = White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

        }

    }*/
    var username = state.username
    var password = state.password
    var passwordVisible = state.passwordVisible
    val isFormValid = username.isNotBlank() && password.length >= 3

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_avows_logo),
            contentDescription = stringResource(R.string.logo),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(240.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.login_with_username), fontSize = 24.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = username,
            shape = RoundedCornerShape(16.dp),
            onValueChange = { onAction(LoginAction.OnUsernameChanged(it)) },
            label = { Text(stringResource(R.string.username)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            shape = RoundedCornerShape(16.dp),
            onValueChange = { onAction(LoginAction.OnPasswordChanged(it)) },
            label = { Text(stringResource(R.string.password)) },
            trailingIcon = {
                IconButton(onClick = { onAction(LoginAction.OnPasswordVisibleChange) }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = stringResource(R.string.toggle_password)
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if(!state.isLoading) {
                    onAction(LoginAction.OnLoginClicked)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            //.padding(horizontal = 16.dp, vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            if(state.isLoading) {
                CircularProgressIndicator(
                    color = White,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text(
                    text = stringResource(R.string.login),
                    color = White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}