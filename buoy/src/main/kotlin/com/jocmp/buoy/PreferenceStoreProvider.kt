package com.jocmp.buoy

interface PreferenceStoreProvider {
    fun build(accountID: String): AccountPreferences

    fun delete(accountID: String)
}
