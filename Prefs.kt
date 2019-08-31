package com.example.crudappkotlin.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context){
    val PREFS_FILENAME = "com.example.crudappkotlin"
    val USER_TOKEN = "user_token"
    val IS_INSERTED = "is_inserted"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);

    var isInseted:Boolean
      get() = prefs.getBoolean(IS_INSERTED,false)
      set(value) = prefs.edit().putBoolean(IS_INSERTED,value).apply()
}