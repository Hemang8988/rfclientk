package com.example.crudappkotlin.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.crudappkotlin.model.UserResponse

@Dao
interface UserDao {
    @Query("SELECT * from User ORDER BY firstName ASC")
    fun getAllUsers(): LiveData<List<UserResponse.User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserResponse.User)
}