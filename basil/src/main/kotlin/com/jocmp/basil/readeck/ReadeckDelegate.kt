package com.jocmp.basil.readeck

import com.jocmp.basil.AccountDelegate
import com.jocmp.basil.AccountPreferences
import com.jocmp.basil.accounts.httpClientBuilder
import com.jocmp.basil.networking.BasicAuthInterceptor
import com.jocmp.basil.networking.UserAgentInterceptor
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
