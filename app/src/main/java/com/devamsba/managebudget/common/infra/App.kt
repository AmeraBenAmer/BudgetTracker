package com.devamsba.managebudget.common.infra

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.devamsba.managebudget.common.infra.utils.LocalizationUtils
import com.maltaisn.icondialog.pack.IconPack
import com.maltaisn.icondialog.pack.IconPackLoader
import com.maltaisn.iconpack.defaultpack.createDefaultIconPack
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application() {
    var iconPack: IconPack? = null

    override fun onCreate() {
        super.onCreate()

        loadIconPick()
    }

    private fun loadIconPick(){
        val loader = IconPackLoader(this)
        // Create an icon pack and load all drawables.
        val iconPack = createDefaultIconPack(loader)
        iconPack.loadDrawables(loader.drawableLoader)

        this.iconPack = iconPack
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocalizationUtils.applyLanguage(base, "ar"))
    }
}