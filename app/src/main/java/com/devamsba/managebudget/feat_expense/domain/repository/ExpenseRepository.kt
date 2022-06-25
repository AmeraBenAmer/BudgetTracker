package com.devamsba.managebudget.feat_expense.domain.repository

import com.devamsba.managebudget.feat_expense.domain.entity.ExpenseEntity
import com.devamsba.managebudget.feat_expense.domain.entity.TotalExpenseByCategory
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {


    suspend fun insert(expense: ExpenseEntity)
    suspend fun update(expense: ExpenseEntity)
    suspend fun delete(expense: ExpenseEntity)
    fun fetchAllExpense(): Flow<List<ExpenseEntity>>
    fun getTotalAmountGroupedByCategory(): Flow<List<TotalExpenseByCategory>>
    fun getTotalAmount(): Flow<Double>


}