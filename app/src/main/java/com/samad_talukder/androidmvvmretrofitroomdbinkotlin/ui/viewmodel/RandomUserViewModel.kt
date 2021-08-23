package com.samad_talukder.androidmvvmretrofitroomdbinkotlin.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.db.ResultTable

import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.model.RandomUserResponse
import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.repository.RandomUserRepository
import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.utils.HandleResource
import kotlinx.coroutines.*
import retrofit2.Response

class RandomUserViewModel(private val randomUserRepository: RandomUserRepository) : ViewModel() {
    val randomUser: MutableLiveData<HandleResource<RandomUserResponse>> = MutableLiveData()
    var job: Job? = null

    fun getRandomUser() = viewModelScope.launch {
        randomUser.postValue(HandleResource.Loading())
        val userResponse = randomUserRepository.getRandomUser()
        randomUser.postValue(handleResponse(userResponse))
    }

    fun getRandomUserWithParameter(resultsCount: Int) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val userResponse = randomUserRepository.getRandomUserWithParameter(resultsCount)
            withContext(Dispatchers.Main) {
                randomUser.postValue(handleResponse(userResponse))
            }
        }

    }

    private fun handleResponse(userResponse: Response<RandomUserResponse>): HandleResource<RandomUserResponse>? {
        if (userResponse.isSuccessful) {
            userResponse.body()?.let { randomUserResponseData ->
                return HandleResource.Success(randomUserResponseData)
            }
        }
        return HandleResource.Error(userResponse.message())
    }



}