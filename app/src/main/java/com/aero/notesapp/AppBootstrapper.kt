package com.aero.notesapp

import android.app.Application
import com.aero.notesapp.core.SvgLoader

class AppBootstrapper():Application() {
    override fun onCreate() {
        super.onCreate()

        SvgLoader.getContext(this)
    }
}