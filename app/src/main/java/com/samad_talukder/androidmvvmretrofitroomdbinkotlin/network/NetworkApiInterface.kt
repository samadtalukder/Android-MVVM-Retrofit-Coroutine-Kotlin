package com.samad_talukder.androidmvvmretrofitroomdbinkotlin.network

import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.model.RandomUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApiInterface {

    @GET(ApiUrl.RANDOM_USER)
    suspend fun getRandomUser():Response<RandomUserResponse>

    @GET(ApiUrl.RANDOM_USER_PARAMETER)
    suspend fun getRandomUserWithParameter(
        @Query("results") resultsCount: Int
    ):Response<RandomUserResponse>
}