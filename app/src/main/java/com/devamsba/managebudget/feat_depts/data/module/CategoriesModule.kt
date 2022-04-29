package com.devamsba.managebudget.feat_depts.data.module

import com.devamsba.managebudget.common.data.AppDatabase
import com.devamsba.managebudget.feat_depts.data.local.DebtsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DebtsModule {

    @Provides
    @Singleton
    fun provideDebtsDao(appDatabase: AppDatabase): DebtsDao{
        return appDatabase.debtsDao()
    }
}