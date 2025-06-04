package com.jocmp.buoy.accounts

fun apiSuffix(url: String): String {
    if (url.endsWith(API_PATH)) {
        return url
    }

    return "${url}${API_PATH}"
}

const val API_PATH = "api/"
