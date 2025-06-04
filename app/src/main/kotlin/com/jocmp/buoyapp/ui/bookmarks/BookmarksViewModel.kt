package com.jocmp.buoyapp.ui.bookmarks

import androidx.lifecycle.ViewModel
import com.jocmp.buoy.Account

class BookmarksViewModel(account: Account): ViewModel() {
    val username = account.username.changes()
}
