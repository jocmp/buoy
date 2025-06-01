package com.jocmp.buoyapp

import org.koin.core.KoinApplication
import org.koin.core.context.loadKoinModules

fun KoinApplication.setupCommonModules() {
    modules(common)
}

fun loadAccountModules() {
//    loadKoinModules(accountModules)
}

//fun unloadAccountModules() {
//    unloadKoinModules(accountModules)
//}

//private val accountModules = listOf(
//    accountModule,
//    settingsModule,
//    articlesModule,
//    refresherModule,
//    syncModule,
//)
