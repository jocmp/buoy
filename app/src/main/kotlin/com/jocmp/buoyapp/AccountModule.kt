package com.jocmp.buoyapp

import com.jocmp.buoy.Account
import com.jocmp.buoy.AccountManager
import com.jocmp.buoyapp.preferences.AppPreferences
import org.koin.dsl.module

val accountModule = module {
    single<Account> {
        get<AccountManager>().findByID(
            id = get<AppPreferences>().accountID.get(),
        )!!
    }
}
