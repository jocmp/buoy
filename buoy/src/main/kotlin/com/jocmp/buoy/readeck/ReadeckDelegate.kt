package com.jocmp.buoy.readeck

import com.jocmp.buoy.AccountDelegate
import com.jocmp.buoy.AccountPreferences
import com.jocmp.buoy.accounts.httpClientBuilder
import com.jocmp.buoy.networking.BasicAuthInterceptor
import com.jocmp.buoy.networking.UserAgentInterceptor
import okhttp3.Credentials
import okhttp3.OkHttpClient
import java.net.URI

class ReadeckDelegate(
    private val httpClient: OkHttpClient,
) : AccountDelegate {
    constructor(cachePath: URI, preferences: AccountPreferences) : this(
        httpClient = httpClientBuilder(cachePath = cachePath)
            .addInterceptor(UserAgentInterceptor())
            .addInterceptor(BasicAuthInterceptor {
                "Bearer ${preferences.token.get()}"
            })
            .build()
    )
}
