package com.jocmp.buoyapp.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jocmp.buoy.AccountManager
import com.jocmp.buoy.accounts.apiSuffix
import com.jocmp.buoy.common.Async
import com.jocmp.buoy.common.launchIO
import com.jocmp.buoy.common.withProtocol
import com.jocmp.buoy.common.withTrailingSeparator
import com.jocmp.buoy.readeck.Credentials
import com.jocmp.buoyapp.loadAccountModules
import com.jocmp.buoyapp.preferences.AppPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel(
    handle: SavedStateHandle,
    private val accountManager: AccountManager,
    private val appPreferences: AppPreferences,
) : ViewModel() {
    private var _username by mutableStateOf("")
    private var _password by mutableStateOf("")
    private var _url by mutableStateOf("")
    private var _result by mutableStateOf<Async<Unit>>(Async.Uninitialized)

    val username
        get() = _username

    val password
        get() = _password

    val url
        get() = _url

    val loading: Boolean
        get() = _result is Async.Loading

    val errorMessage: String?
        get() = (_result as? Async.Failure)?.error?.message

    fun setUsername(username: String) {
        _username = username
    }

    fun setPassword(password: String) {
        _password = password
    }

    fun setURL(url: String) {
        _url = url
    }

    fun submit(onSuccess: () -> Unit) {
        if (username.isBlank() || password.isBlank()) {
            _result = Async.Failure(loginError())
        }

        normalizeURL()

        viewModelScope.launchIO {
            _result = Async.Loading

            credentials.verify()
                .onSuccess { result ->
                    createAccount(result)

                    withContext(Dispatchers.Main) {
                        onSuccess()
                    }
                }
                .onFailure {
                    _result = Async.Failure(it)
                }
        }
    }

    private fun normalizeURL() {
        if (_url.isBlank()) {
            return
        }

        _url = _url
            .withProtocol
            .withTrailingSeparator
            .let { apiSuffix(it) }
    }

    private val credentials: Credentials
        get() = Credentials(
            username = username,
            secret = password,
            url = _url
        )

    private fun createAccount(credentials: Credentials) {
        val accountID = accountManager.createAccount(
            username = credentials.username,
            token = credentials.secret,
            url = credentials.url,
        )

        selectAccount(accountID)

        loadAccountModules()
    }

    private fun selectAccount(id: String) {
        appPreferences.accountID.set(id)
    }

    private fun loginError() = Error("Error logging in")
}
