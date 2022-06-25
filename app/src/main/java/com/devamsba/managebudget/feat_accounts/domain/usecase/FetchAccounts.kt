package com.devamsba.managebudget.feat_accounts.domain.usecase

import com.devamsba.managebudget.feat_accounts.domain.entity.AccountsEntity
import com.devamsba.managebudget.feat_accounts.domain.repository.AccountsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAccounts @Inject constructor(private val repository: AccountsRepository){

    fun invoke(): Flow<List<AccountsEntity>> {
        return repository.getAll()
    }
}