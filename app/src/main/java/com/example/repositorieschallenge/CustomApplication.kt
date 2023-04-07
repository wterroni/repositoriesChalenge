package com.example.repositorieschallenge

import android.app.Application
import com.example.repositorieschallenge.di.startKoin

class CustomApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin (this@CustomApplication)
    }
}