package com.jocmp.buoyapp.preferences

import android.content.Context
import androidx.preference.PreferenceManager
import com.jocmp.buoy.preferences.AndroidPreferenceStore
import com.jocmp.buoy.preferences.Preference
import com.jocmp.buoy.preferences.PreferenceStore

class AppPreferences(context: Context) {
    private val preferenceStore: PreferenceStore = AndroidPreferenceStore(
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    )

    val accountID: Preference<String>
        get() = preferenceStore.getString("account_id")
}
