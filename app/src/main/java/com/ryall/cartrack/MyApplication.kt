package com.ryall.cartrack

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application() {
    companion object {
        lateinit var myApplicationContext: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        myApplicationContext = this
    }
}