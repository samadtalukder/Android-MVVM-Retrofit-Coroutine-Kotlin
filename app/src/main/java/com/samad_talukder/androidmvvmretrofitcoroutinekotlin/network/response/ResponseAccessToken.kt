package com.samad_talukder.androidmvvmretrofitcoroutinekotlin.network.response

data class ResponseAccessToken(
    val `data`: AccessTokenData? = null,
    val message: String? = null,
    val success: Boolean? = null
)

data class AccessTokenData(
    val access_token: String? = null
)
