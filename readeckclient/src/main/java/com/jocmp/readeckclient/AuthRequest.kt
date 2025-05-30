package com.jocmp.readeckclient

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthRequest(
    val application: String,
    val username: String,
    val password: String,
    val roles: List<String>
)
