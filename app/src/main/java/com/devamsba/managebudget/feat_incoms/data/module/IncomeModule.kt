package com.devamsba.managebudget.feat_incoms.data.module

import com.devamsba.managebudget.common.data.AppDatabase
import com.devamsba.managebudget.feat_incoms.data.local.IncomeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object IncomeModule {

    @Provides
    @Singleton
    fun provideIncomeDao(appDatabase: AppDatabase): IncomeDao {
        return appDatabase.incomeDao()
    }
}