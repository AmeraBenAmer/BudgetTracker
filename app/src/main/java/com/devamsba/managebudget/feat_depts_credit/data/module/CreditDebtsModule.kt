package com.devamsba.managebudget.feat_depts_credit.data.module

import com.devamsba.managebudget.common.data.AppDatabase
import com.devamsba.managebudget.feat_depts_credit.data.local.CreditDebtsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CreditDebtsModule {

    @Provides
    @Singleton
    fun provideCreditDebtsDao(appDatabase: AppDatabase): CreditDebtsDao{
        return appDatabase.creditDebtsDao()
    }
}