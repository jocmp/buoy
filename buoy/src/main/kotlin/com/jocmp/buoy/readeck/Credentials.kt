package com.jocmp.buoy.readeck

import com.jocmp.readeckclient.Readeck

data class ReadeckCredentials(
    val username: String,
    val secret: String,
    val url: String,
) {
    suspend fun verify(): Result<ReadeckCredentials> {
        try {
            val response = Readeck.verifyCredentials(
                username = username,
                password = secret,
                baseURL = url,
                application = APPLICATION,
                roles = roles,
            )

            val responseBody = response.body()

            return if (response.isSuccessful && responseBody != null) {
                Result.success(copy(secret = responseBody.token))
            } else {
                Result.failure(Throwable("Failed with status ${response.code()} ${response.message()}"))
            }
        } catch (e: Throwable) {
            return Result.failure(e)
        }
    }

    companion object {
        const val APPLICATION = "Buoy"

        val roles = listOf(
            "scoped_bookmarks_r",
            "scoped_bookmarks_w",
        )
    }
}
