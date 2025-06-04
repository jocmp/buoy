package com.jocmp.buoyapp

import com.jocmp.buoy.AccountManager
import com.jocmp.buoy.PreferenceStoreProvider
import com.jocmp.buoy.common.SharedPreferenceStoreProvider
import com.jocmp.buoyapp.preferences.AppPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val common = module {
    single<PreferenceStoreProvider> { SharedPreferenceStoreProvider(get()) }
    single {
        AccountManager(
            rootFolder = androidContext().filesDir.toURI(),
            cacheDirectory = androidContext().cacheDir.toURI(),
            preferenceStoreProvider = get(),
        )
    }
    single { AppPreferences(get()) }
}
