package com.example.crudappkotlin

import android.app.Application
import com.example.crudappkotlin.utils.Prefs

class App : Application() {
    companion object {
        var prefs: Prefs? = null
    }

    override fun onCreate() {
        prefs = Prefs(applicationContext)
        super.onCreate()
    }
}