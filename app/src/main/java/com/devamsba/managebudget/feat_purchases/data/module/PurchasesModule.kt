package com.devamsba.managebudget.feat_purchases.data.module

import com.devamsba.managebudget.common.data.AppDatabase
import com.devamsba.managebudget.feat_purchases.data.local.PurchasesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PurchasesModule {

    @Provides
    @Singleton
    fun providePurchasesDao(appDatabase: AppDatabase): PurchasesDao {
        return appDatabase.purchasesDao()
    }
}