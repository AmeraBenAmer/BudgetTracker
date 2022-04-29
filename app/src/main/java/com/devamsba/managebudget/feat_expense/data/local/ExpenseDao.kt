package com.devamsba.managebudget.feat_expense.data.local

import androidx.room.*
import com.devamsba.managebudget.common.data.BaseDao
import com.devamsba.managebudget.common.data.expense.entity.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ExpenseDao : BaseDao<ExpenseEntity> {

    @Query("SELECT * FROM expense")
    abstract fun getAllExpense(): Flow<List<ExpenseEntity>>

}