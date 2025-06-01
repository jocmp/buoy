package com.jocmp.buoy

import com.jocmp.buoy.preferences.PreferenceStore
import com.jocmp.buoy.preferences.Preference

class AccountPreferences(
    private val store: PreferenceStore,
) {
    val url: Preference<String>
        get() = store.getString("api_url", "")

    val username: Preference<String>
        get() = store.getString("username", "")

    val token: Preference<String>
        get() = store.getString("password", "")
}
