package com.devamsba.managebudget.common.infra

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.devamsba.managebudget.common.infra.utils.LocalizationUtils
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocalizationUtils.applyLanguage(base, "ar"))
    }
}