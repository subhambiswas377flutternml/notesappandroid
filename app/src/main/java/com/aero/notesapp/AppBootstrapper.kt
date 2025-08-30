package com.aero.notesapp

import android.app.Application
import com.aero.notesapp.core.SvgLoader
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppBootstrapper():Application() {
    override fun onCreate() {
        super.onCreate()

        SvgLoader.getContext(this)
    }
}