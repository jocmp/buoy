package com.jocmp.buoyapp.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jocmp.buoyapp.R
import com.jocmp.buoyapp.setupCommonModules
import com.jocmp.buoyapp.ui.widthMaxSingleColumn
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication

@Composable
fun LoginView(
    onUsernameChange: (username: String) -> Unit = {},
    onPasswordChange: (password: String) -> Unit = {},
    onUrlChange: (url: String) -> Unit = {},
    onSubmit: () -> Unit = {},
    url: String,
    username: String,
    password: String,
    loading: Boolean = false,
    errorMessage: String? = null,
) {
    Scaffold { padding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .widthMaxSingleColumn()
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 56.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.auth_title_readeck),
                        style = typography.headlineMedium,
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        UrlField(
                            onChange = onUrlChange,
                            url = url,
                            placeholder = {
                                Text(stringResource(R.string.auth_server_url_placeholder))
                            }
                        )
                        AuthFields(
                            username = username,
                            password = password,
                            onUsernameChange = onUsernameChange,
                            onPasswordChange = onPasswordChange,
                            onSubmit = onSubmit,
                            loading = loading,
                            errorMessage = errorMessage,
                            prompt = {
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UrlField(
    onChange: (url: String) -> Unit,
    placeholder: (@Composable () -> Unit)? = null,
    url: String,
) {
    TextField(
        value = url,
        onValueChange = onChange,
        singleLine = true,
        placeholder = placeholder,
        label = {
            Text(stringResource(R.string.auth_server_url))
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Uri,
        ),
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Preview
@Composable
private fun LoginViewPreview() {
    val context = LocalContext.current

    KoinApplication(
        application = {
            androidContext(context)
            setupCommonModules()
        }
    ) {
        LoginView(
            url = "",
            username = "test@example.com",
            password = "",
        )
    }
}
