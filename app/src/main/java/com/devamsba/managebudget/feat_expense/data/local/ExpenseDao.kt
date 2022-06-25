package com.devamsba.managebudget.feat_expense.data.local

import androidx.room.Dao
import androidx.room.Query
import com.devamsba.managebudget.common.data.BaseDao
import com.devamsba.managebudget.feat_expense.domain.entity.ExpenseEntity
import com.devamsba.managebudget.feat_expense.domain.entity.TotalExpenseByCategory
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ExpenseDao : BaseDao<ExpenseEntity> {

    @Query("SELECT * FROM expense_tbl")
    abstract fun getAllExpense(): Flow<List<ExpenseEntity>>

    @Query("SELECT id_fk_category, sum(amount) as totalAmount FROM expense_tbl GROUP BY id_fk_category")
    abstract fun getTotalAmountGroupedByCategory(): Flow<List<TotalExpenseByCategory>>

    @Query("SELECT sum(amount) as totalAmount FROM expense_tbl ")
    abstract fun getTotalAmount(): Flow<Double>

}