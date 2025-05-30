package com.jocmp.readeckclient

import okhttp3.OkHttpClient
import com.squareup.moshi.Moshi
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Readeck {
    @GET("bookmarks")
    suspend fun entries(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = 0,
    ): Response<List<Bookmark>>

    @POST("auth")
    suspend fun auth(
        @Body body: AuthRequest
    ): Response<AuthResponse>

    companion object {
        fun create(
            client: OkHttpClient,
            baseURL: String
        ): Readeck {
            val moshi = Moshi.Builder().build()

            return Retrofit.Builder()
                .client(client)
                .baseUrl(baseURL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create()
        }

        suspend fun verifyCredentials(
            username: String,
            password: String,
            baseURL: String,
            application: String,
            roles: List<String>,
            client: OkHttpClient = OkHttpClient(),
        ): Response<AuthResponse> {
            val readeck = create(client = client, baseURL = baseURL)

            return readeck.auth(
                AuthRequest(
                    application = application,
                    username = username,
                    password = password,
                    roles = roles
                )
            )
        }
    }
}
