package com.jocmp.buoy.common

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit
import com.jocmp.buoy.AccountPreferences
import com.jocmp.buoy.PreferenceStoreProvider
import com.jocmp.buoy.preferences.AndroidPreferenceStore

class SharedPreferenceStoreProvider(
    private val context: Context
) : PreferenceStoreProvider {
    override fun build(accountID: String): AccountPreferences {
        return AccountPreferences(
            AndroidPreferenceStore(
                buildPreferences(context, accountID)
            )
        )
    }

    override fun delete(accountID: String) {
        val preferences = buildPreferences(context, accountID)

        preferences.edit(commit = true) {
            clear()
        }
    }
}

private fun buildPreferences(context: Context, accountID: String) =
    context.getSharedPreferences(accountPrefs(accountID), MODE_PRIVATE)

private fun accountPrefs(accountID: String) = "account_$accountID"
