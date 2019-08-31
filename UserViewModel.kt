package com.example.crudappkotlin.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.crudappkotlin.App
import com.example.crudappkotlin.database.UserDatabase
import com.example.crudappkotlin.model.ErrorObj
import com.example.crudappkotlin.model.UserResponse
import com.example.crudappkotlin.repository.UserRepository
import com.sampletopicapp.apis.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "UserViewModel"

    private val repository: UserRepository
    val allUser: LiveData<List<UserResponse.User>>
    val errorObj = MutableLiveData<ErrorObj>()

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        allUser = repository.allUsers
    }

    fun insert(user: UserResponse.User) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(user)
    }

    fun callUserApi() {
        ApiService.create().fetchAllTopics().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                if (result.isSuccessful && result.body() != null) {
                    result.body()!!.data?.let {
                        for (user: UserResponse.User in it) {
                            insert(user)
                        }
                        App.prefs!!.isInseted = true
                        Log.d("Insertion", "Success")
                        errorObj.value = ErrorObj(0, "User Insertion done")
                    }
                }else{
                    result.message()?.let { errorObj.value = ErrorObj(1,it) }
                }
            }, { error ->
                error.message?.let { errorObj.value = ErrorObj(1,it)}
                error.printStackTrace()
            })
    }
}