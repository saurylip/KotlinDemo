package com.shy.kotlindemo1

import android.app.Application
import android.content.Context

class ShyApp : Application() {

    companion object {
        lateinit var context : Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}