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
}