package com.samad_talukder.androidmvvmretrofitcoroutinekotlin.network.request

data class RequestLocationUpdate(
    val area: String? = null,
    val city: String? = null,
    val country: String? = null,
    val district: String? = null,
    val user_id: String? = null
)