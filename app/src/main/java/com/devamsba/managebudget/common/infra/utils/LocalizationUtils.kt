package com.devamsba.managebudget.common.infra.utils

import android.content.Context
import android.os.Build
import java.util.*


object LocalizationUtils {

    fun applyLanguage(context: Context, language: String): Context{
        val locale = Locale(language)
        val configration = context.resources.configuration
        val displayMetrics = context.resources.displayMetrics

        Locale.setDefault(locale)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            configration.setLocale(locale)
            configration.setLayoutDirection(locale)
            context.createConfigurationContext(configration)
        }else{
            configration.locale = locale
            context.resources.updateConfiguration(configration, displayMetrics)
            context
        }


    }

}