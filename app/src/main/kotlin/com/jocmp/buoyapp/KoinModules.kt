package com.jocmp.buoyapp

import com.jocmp.buoyapp.ui.bookmarks.bookmarksModule
import com.jocmp.buoyapp.ui.login.loginModule
import org.koin.core.KoinApplication
import org.koin.core.context.loadKoinModules

fun KoinApplication.setupCommonModules() {
    modules(common, loginModule)
}

fun loadAccountModules() {
    loadKoinModules(accountModules)
}

//fun unloadAccountModules() {
//    unloadKoinModules(accountModules)
//}

private val accountModules = listOf(
    accountModule,
    bookmarksModule,
//    settingsModule,
//    refresherModule,
//    syncModule,
)
