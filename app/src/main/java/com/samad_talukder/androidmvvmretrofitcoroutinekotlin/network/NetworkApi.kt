package com.samad_talukder.androidmvvmretrofitcoroutinekotlin.network

import com.samad_talukder.androidmvvmretrofitcoroutinekotlin.network.response.ResponseAccessToken
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {

    @GET(ApiUrl.URL_GET_ACCESS_TOKEN)
    suspend fun getAccessTokenApi(): Response<ResponseAccessToken>
}