package com.example.inventory.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var id: Int = 0,

    @ColumnInfo(name = "user_phone_number")
    var phoneNumber: String,

    @ColumnInfo(name = "user_password")
    var passWord: String

    )
