package com.jocmp.buoyapp.ui.login

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel {
        LoginViewModel(
            handle = get(),
            accountManager = get(),
            appPreferences = get()
        )
    }
//    viewModel {
//        UpdateLoginViewModel(
//            account = get()
//        )
//    }
}
