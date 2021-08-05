package com.samad_talukder.androidmvvmretrofitcoroutinekotlin.ui.view_model

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.samad_talukder.androidmvvmretrofitcoroutinekotlin.app.MainApplication
import com.samad_talukder.androidmvvmretrofitcoroutinekotlin.network.ResponseListener
import com.samad_talukder.androidmvvmretrofitcoroutinekotlin.network.response.ResponseAccessToken
import com.samad_talukder.androidmvvmretrofitcoroutinekotlin.repository.MainRepository
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response

class MainViewModel(app: Application, private val newsRepository: MainRepository) :

    AndroidViewModel(app) {

    val accessToken: MutableLiveData<ResponseListener<ResponseAccessToken>> = MutableLiveData()
    var accessTokenResponse: ResponseAccessToken? = null


    fun getAccessToken() = viewModelScope.launch {
        safeBreakingNewsCall()
    }

    private suspend fun safeBreakingNewsCall() {
        accessToken.postValue(ResponseListener.Loading())
        try {
            if (hasInternetConnection()) {
                val response = newsRepository.getAccessToken()
                accessToken.postValue(handleBreakingNewsResponse(response))
            } else {
                accessToken.postValue(ResponseListener.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> accessToken.postValue(ResponseListener.Error("Network Failure"))
                else -> accessToken.postValue(ResponseListener.Error("Conversion Error"))
            }
        }
    }

    private fun handleBreakingNewsResponse(response: Response<ResponseAccessToken>): ResponseListener<ResponseAccessToken> {

        if (response.isSuccessful) {

            response.body()?.let { resultResponse ->

                if (accessTokenResponse == null) {

                    accessTokenResponse = resultResponse

                } else {

                    val oldArticles = accessTokenResponse?.data

                }

                return ResponseListener.Success(accessTokenResponse ?: resultResponse)
            }
        }
        return ResponseListener.Error(response.message())
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<MainApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false

        }
        //return false
    }
}












