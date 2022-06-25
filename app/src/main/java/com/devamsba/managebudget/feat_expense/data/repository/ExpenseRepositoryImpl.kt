package com.devamsba.managebudget.feat_expense.data.repository

import com.devamsba.managebudget.common.data.AppDatabase
import com.devamsba.managebudget.feat_expense.domain.entity.ExpenseEntity
import com.devamsba.managebudget.feat_expense.domain.entity.TotalExpenseByCategory
import com.devamsba.managebudget.feat_expense.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(private val db: AppDatabase) : ExpenseRepository {
    override suspend fun insert(expense: ExpenseEntity) = db.expenseDao().insert(expense)

    override suspend fun update(expense: ExpenseEntity) = db.expenseDao().update(expense)

    override suspend fun delete(expense: ExpenseEntity) = db.expenseDao().delete(expense)

    override fun fetchAllExpense() = db.expenseDao().getAllExpense()
    override fun getTotalAmountGroupedByCategory(): Flow<List<TotalExpenseByCategory>>  = db.expenseDao().getTotalAmountGroupedByCategory()

    override fun getTotalAmount(): Flow<Double> = db.expenseDao().getTotalAmount()
//    override fun getTotalAmountGroupedByCategory(): Flow<List<TotalExpenseByCategory>> = db.expenseDao().getTotalAmountGroupedByCategory()
//    override fun getTotalAmount(): Flow<Double> = db.expenseDao().getTotalAmount()

}
