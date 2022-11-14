package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user : User)

    @Query("SELECT * from user WHERE user_id = :userid")
    fun getUserDetails(userid: Int) : Flow<User>

    @Query("SELECT * from user WHERE user_phone_number = :userPhoneNUmber AND user_password = :userPassword")
    fun getUserDetailsByPhoneNumber(userPhoneNUmber: String, userPassword: String)  : Flow<User>
}