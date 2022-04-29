package com.devamsba.managebudget.common.domain.datastore

import android.content.Context
 import com.devamsba.managebudget.common.data.datastore.UserSettingSerializer
import javax.inject.Inject
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.devamsba.managebudget.common.data.userSettings.LanguageSettings
import com.devamsba.managebudget.common.data.userSettings.ThemeSettings
import com.devamsba.managebudget.common.data.userSettings.UserSettings
import kotlinx.coroutines.flow.map

class UserSettingManager @Inject constructor(context: Context) {

    private val dataStore = context.settingsDataStore

    val settings get() = dataStore.data
    val themeMode get() = dataStore.data.map { it.theme }
    val notification get() = dataStore.data.map { it.notifications }
    val language get() = dataStore.data.map { it.language }


    suspend fun setSetting(setting: UserSettings){
        dataStore.updateData { setting }
    }
    suspend fun setThemeMode(themeSetting : ThemeSettings){
        dataStore.updateData {
            it.toBuilder().setTheme(themeSetting).build()
        }
    }

    suspend fun setNotificationSetting(notification: Boolean){
        dataStore.updateData {
            it.toBuilder().setNotifications(notification).build()
        }
    }

    suspend fun setLanguageSetting(languageSettings: LanguageSettings){
        dataStore.updateData {
            it.toBuilder().setLanguage(languageSettings).build()
        }
    }

    companion object {
        private val Context.settingsDataStore: DataStore<UserSettings> by dataStore(
            fileName = "settings.pd",
            serializer = UserSettingSerializer
        )
    }
}

