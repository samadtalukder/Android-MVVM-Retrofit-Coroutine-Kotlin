package com.samad_talukder.androidmvvmretrofitroomdbinkotlin.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.model.*
import java.io.Serializable

@Entity(tableName = "result_tab")
data class ResultTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val cell: String,
    val email: String,
    val gender: String,
    val nat: String,
    val phone: String,

):Serializable