package com.devamsba.managebudget.feat_accounts.domain.repository

import com.devamsba.managebudget.feat_accounts.domain.entity.AccountsEntity
import kotlinx.coroutines.flow.Flow

interface AccountsRepository{
    suspend fun insert(account: AccountsEntity)
    suspend fun insertAll(accounts: ArrayList<AccountsEntity>)
    suspend fun update(account: AccountsEntity)
    suspend fun delete(account: AccountsEntity)
    fun getAll(): Flow<List<AccountsEntity>>

}