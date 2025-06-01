package com.jocmp.buoyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jocmp.buoyapp.preferences.AppPreferences
import com.jocmp.buoyapp.ui.App
import com.jocmp.buoyapp.ui.Route
import com.jocmp.buoyapp.ui.theme.BuoyTheme
import org.koin.android.ext.android.get
import org.koin.core.context.GlobalContext.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BuoyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    Box(Modifier.padding(padding)) {
                        App(startDestination = startDestination())
                    }
                }
            }
        }
    }

    private fun startDestination(): Route {
        val accountID = get<AppPreferences>().accountID.get()

        return if (accountID.isBlank()) {
            return Route.Login
        } else {
            Route.Settings
        }
    }
}
