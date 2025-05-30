package com.jocmp.basilapp.preferences

import android.content.Context
import androidx.preference.PreferenceManager
import com.jocmp.basil.preferences.AndroidPreferenceStore
import com.jocmp.basil.preferences.Preference
import com.jocmp.basil.preferences.PreferenceStore

class AppPreferences(context: Context) {
    private val preferenceStore: PreferenceStore = AndroidPreferenceStore(
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    )

    val accountID: Preference<String>
        get() = preferenceStore.getString("account_id")
}
