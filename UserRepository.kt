package com.example.crudappkotlin.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.crudappkotlin.database.UserDao
import com.example.crudappkotlin.model.UserResponse

class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<UserResponse.User>> = userDao.getAllUsers()

    @WorkerThread
    suspend fun insert(user: UserResponse.User) {
        userDao.insert(user)
    }
}