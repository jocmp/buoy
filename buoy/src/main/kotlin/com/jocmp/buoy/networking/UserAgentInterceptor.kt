package com.jocmp.buoy.networking

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class UserAgentInterceptor : Interceptor {
    override fun intercept(chain: Chain): Response {
        val originalRequest = chain.request()
        val requestWithUserAgent = originalRequest.newBuilder()
            .header("User-Agent", USER_AGENT)
            .build()
        return chain.proceed(requestWithUserAgent)
    }

    companion object {
        const val USER_AGENT = "Buoy (Read-it-later; https://jocmp.com/)"
    }
}
