package com.devamsba.managebudget.feat_expense.data.module

import com.devamsba.managebudget.common.data.AppDatabase
import com.devamsba.managebudget.feat_expense.data.local.ExpenseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ExpenseModule {

    @Provides
    @Singleton
    fun provideExpenseDao(appDatabase: AppDatabase): ExpenseDao{
        return appDatabase.expenseDao()
    }
}