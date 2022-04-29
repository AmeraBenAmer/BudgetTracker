package com.devamsba.managebudget.common.infra.di

import android.app.Application
import android.content.Context
import com.devamsba.managebudget.common.domain.datastore.UserSettingManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context{
        return application
    }


    @Provides
    @Singleton
    internal  fun provideUserSetting(context: Context) : UserSettingManager{
        return UserSettingManager(context)
    }


}