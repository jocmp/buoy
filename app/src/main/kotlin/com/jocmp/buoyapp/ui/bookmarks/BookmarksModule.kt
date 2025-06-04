package com.jocmp.buoyapp.ui.bookmarks

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookmarksModule =  module {
    viewModel {
        BookmarksViewModel(account = get())
    }
}
