package com.samad_talukder.androidmvvmretrofitcoroutinekotlin.repository

import com.samad_talukder.androidmvvmretrofitcoroutinekotlin.network.NetworkInstance

class MainRepository() {

    suspend fun getAccessToken() = NetworkInstance.api.getAccessTokenApi()


}