package com.samad_talukder.androidmvvmretrofitcoroutinekotlin.network

sealed class ResponseListener<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ResponseListener<T>(data)
    class Error<T>(message: String, data: T? = null) : ResponseListener<T>(data, message)
    class Loading<T> : ResponseListener<T>()
}