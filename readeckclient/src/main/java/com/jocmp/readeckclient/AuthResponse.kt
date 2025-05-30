package com.jocmp.readeckclient

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponse(
  val id: String,
  val token: String
)
