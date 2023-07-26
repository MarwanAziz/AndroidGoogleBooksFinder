package com.example.googlebooksfinder

import android.app.Application
import android.content.Context

class GoogleBooksFinderApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {

        lateinit  var appContext: Context

    }
}