package com.samad_talukder.androidmvvmretrofitroomdbinkotlin.repository

import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.db.ResultTable
import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.db.UserDB
import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.network.ApiInstance

class RandomUserRepository(private val userDB: UserDB) {
    suspend fun getRandomUser() = ApiInstance.apiInterface.getRandomUser()
    suspend fun getRandomUserWithParameter(resultsCount:Int) = ApiInstance.apiInterface.getRandomUserWithParameter(resultsCount)
    suspend fun addUserFav(result:ResultTable) = userDB.getUsersDao().addFav(result)
}