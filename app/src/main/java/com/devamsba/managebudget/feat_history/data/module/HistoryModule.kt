package com.devamsba.managebudget.feat_depts.data.module

import com.devamsba.managebudget.common.data.AppDatabase
import com.devamsba.managebudget.feat_history.data.local.HistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HistoryModule {

    @Provides
    @Singleton
    fun provideHistoryDao(appDatabase: AppDatabase): HistoryDao{
        return appDatabase.historyDao()
    }
}