package com.github.greysteklo.anotherone.calculator.app

import android.app.Application
import com.github.greysteklo.anotherone.calculator.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CalculatorApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
