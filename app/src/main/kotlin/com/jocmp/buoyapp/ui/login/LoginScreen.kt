package com.jocmp.buoyapp.ui.login

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onSuccess: () -> Unit,
) {
    LoginView(
        onUsernameChange = {
            viewModel.setUsername(it)
        },
        onPasswordChange = {
            viewModel.setPassword(it)
        },
        onUrlChange = {
            viewModel.setURL(it)
        },
        onSubmit = {
            viewModel.submit { onSuccess() }
        },
        url = viewModel.url,
        username = viewModel.username,
        password = viewModel.password,
        loading = viewModel.loading,
        errorMessage = viewModel.errorMessage
    )
}
