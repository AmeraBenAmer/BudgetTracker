package com.devamsba.managebudget.feat_accounts.domain.usecase

import com.devamsba.managebudget.feat_accounts.domain.entity.AccountsEntity
import com.devamsba.managebudget.feat_accounts.domain.repository.AccountsRepository
import javax.inject.Inject

class DeleteAccounts @Inject constructor(private val repository: AccountsRepository){
    suspend operator fun invoke(account: AccountsEntity) = repository.delete(account)

}


