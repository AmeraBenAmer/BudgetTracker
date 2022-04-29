package com.devamsba.managebudget.common.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devamsba.managebudget.feat_depts.data.local.DebtsDao
import com.devamsba.managebudget.feat_expense.data.local.ExpenseDao
import com.devamsba.managebudget.feat_history.data.local.HistoryDao
import com.devamsba.managebudget.feat_purchases.data.local.PurchasesDao
import com.devamsba.managebudget.feat_incoms.data.local.IncomeDao
import com.devamsba.managebudget.feat_accounts.data.local.AccountsDao
import com.devamsba.managebudget.feat_accounts.domain.entity.AccountsEntity
import com.devamsba.managebudget.feat_categries.data.local.CategoriesDao


@Database(
    entities = [
        AccountsEntity::class,
        DebtsEntity::class,
        ExpenseEntity::class,
        HistoryEntity::class,
        IncomeEntity::class,
        PurchasesEntity::class,
        TypeMoneyEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao() : ExpenseDao
    abstract fun debtsDao(): DebtsDao
    abstract fun historyDao(): HistoryDao
    abstract fun incomeDao(): IncomeDao
    abstract fun purchasesDao(): PurchasesDao
    abstract fun accountsDao(): AccountsDao
    abstract fun categoryDao(): CategoriesDao
}