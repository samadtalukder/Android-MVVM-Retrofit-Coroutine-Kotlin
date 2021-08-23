package com.samad_talukder.androidmvvmretrofitroomdbinkotlin.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFav(result: ResultTable): Long

    @Query("SELECT * FROM result_tab")
    fun getFavUser():LiveData<List<ResultTable>>
}