package com.jocmp.basilapp

import com.jocmp.basil.AccountManager
import com.jocmp.basil.PreferenceStoreProvider
import com.jocmp.basil.common.SharedPreferenceStoreProvider
import com.jocmp.basilapp.preferences.AppPreferences
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
