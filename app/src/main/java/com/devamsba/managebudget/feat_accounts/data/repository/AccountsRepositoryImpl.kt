package com.devamsba.managebudget.feat_accounts.data.repository

import com.devamsba.managebudget.common.data.AppDatabase
import com.devamsba.managebudget.feat_accounts.domain.entity.AccountsEntity
import com.devamsba.managebudget.feat_accounts.domain.repository.AccountsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountsRepositoryImpl @Inject constructor(
    private val db: AppDatabase) :AccountsRepository{
    override suspend fun insert(account: AccountsEntity) = db.accountsDao().insert(account)

    override suspend fun insertAll(accounts: ArrayList<AccountsEntity>) = db.accountsDao().insertAll(accounts)

    override suspend fun update(account: AccountsEntity) = db.accountsDao().update(account)

    override suspend fun delete(account: AccountsEntity) = db.accountsDao().delete(account)

    override fun getAll(): Flow<List<AccountsEntity>> = db.accountsDao().


}