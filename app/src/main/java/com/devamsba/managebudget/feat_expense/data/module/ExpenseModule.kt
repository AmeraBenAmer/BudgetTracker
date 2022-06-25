package com.devamsba.managebudget.feat_expense.data.module

import com.devamsba.managebudget.common.data.AppDatabase
import com.devamsba.managebudget.feat_expense.data.local.ExpenseDao
import com.devamsba.managebudget.feat_expense.data.repository.ExpenseRepositoryImpl
import com.devamsba.managebudget.feat_expense.domain.repository.ExpenseRepository
import com.devamsba.managebudget.feat_expense.domain.usecase.*
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
    fun provideExpenseDao(appDatabase: AppDatabase): ExpenseDao {
        return appDatabase.expenseDao()
    }


    @Provides
    @Singleton
    fun provideExpenseUseCase(repository: ExpenseRepository): ExpenseUseCase {
        return ExpenseUseCase(
            insertExpense = InsertExpense(repository),
            updateExpense = UpdateExpense(repository),
            fetchExpense = FetchExpense(repository),
            deleteExpense = DeleteExpense(repository)
        )
    }

    @Provides
    @Singleton
    fun provideRepository(appDatabase: AppDatabase): ExpenseRepository {
        return ExpenseRepositoryImpl(appDatabase)
    }
}