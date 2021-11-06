package com.example.letterapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LetterApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}