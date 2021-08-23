package com.samad_talukder.androidmvvmretrofitroomdbinkotlin.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.repository.RandomUserRepository

class ViewModelProviderFactory(private val randomUserRepository: RandomUserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RandomUserViewModel(randomUserRepository) as T
    }


}