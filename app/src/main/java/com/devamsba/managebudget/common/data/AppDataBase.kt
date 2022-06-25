package com.devamsba.managebudget.common.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devamsba.managebudget.feat_accounts.data.local.AccountsDao
import com.devamsba.managebudget.feat_accounts.domain.entity.AccountsEntity
import com.devamsba.managebudget.feat_categries.data.local.CategoriesDao
import com.devamsba.managebudget.feat_categries.domain.entity.CategoriesEntity
import com.devamsba.managebudget.feat_depts_credit.data.local.CreditDebtsDao
import com.devamsba.managebudget.feat_depts_credit.domain.entity.CreditDebtsEntity
import com.devamsba.managebudget.feat_expense.data.local.ExpenseDao
import com.devamsba.managebudget.feat_expense.domain.entity.ExpenseEntity
import com.devamsba.managebudget.feat_history.data.local.HistoryDao
import com.devamsba.managebudget.feat_history.domain.entity.HistoryEntity
import com.devamsba.managebudget.feat_incoms.data.local.IncomeDao
import com.devamsba.managebudget.feat_incoms.domain.entity.IncomeEntity
import com.devamsba.managebudget.feat_purchases.data.local.PurchasesDao
import com.devamsba.managebudget.feat_purchases.domain.entity.ItemDetails
import com.devamsba.managebudget.feat_purchases.domain.entity.PurchasesEntity


@Database(
    entities = [
        AccountsEntity::class,
        CreditDebtsEntity::class,
        ExpenseEntity::class,
        HistoryEntity::class,
        IncomeEntity::class,
        PurchasesEntity::class,
        CategoriesEntity::class,
        ItemDetails::class,
    ],
    version = 11,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun creditDebtsDao(): CreditDebtsDao
    abstract fun historyDao(): HistoryDao
    abstract fun incomeDao(): IncomeDao
    abstract fun purchasesDao(): PurchasesDao
    abstract fun accountsDao(): AccountsDao
    abstract fun categoryDao(): CategoriesDao
}