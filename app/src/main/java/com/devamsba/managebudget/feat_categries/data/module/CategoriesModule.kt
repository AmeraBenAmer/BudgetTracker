package com.devamsba.managebudget.feat_categries.data.module

import com.devamsba.managebudget.feat_categries.data.local.CategoriesDao
import com.devamsba.managebudget.common.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CategoriesModule {

    @Provides
    @Singleton
    fun provideCategoriesDao(appDatabase: AppDatabase): CategoriesDao{
        return appDatabase.categoryDao()
    }
}