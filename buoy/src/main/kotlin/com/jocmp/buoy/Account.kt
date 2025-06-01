package com.jocmp.buoy

import com.jocmp.buoy.readeck.ReadeckDelegate
import okhttp3.OkHttpClient
import java.net.URI

data class Account(
    val id: String,
    val path: URI,
    val cacheDirectory: URI,
    val preferences: AccountPreferences,
    val delegate: AccountDelegate = ReadeckDelegate(cacheDirectory, preferences)
)
