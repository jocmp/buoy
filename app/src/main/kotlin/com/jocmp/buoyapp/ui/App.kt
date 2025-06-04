package com.jocmp.buoyapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jocmp.buoyapp.ui.bookmarks.BookmarkScreen
import com.jocmp.buoyapp.ui.login.LoginScreen
import com.jocmp.buoyapp.ui.theme.BuoyTheme

@Composable
fun App(
    startDestination: Route,
) {
    val navController = rememberNavController()

    BuoyTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                composable<Route.Articles> {
                    BookmarkScreen()
                }
                composable<Route.Login> {
                    LoginScreen(
                        onSuccess = {
                            navController.navigate(Route.Articles) {
                                launchSingleTop = true

                                popUpTo(Route.Login) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
