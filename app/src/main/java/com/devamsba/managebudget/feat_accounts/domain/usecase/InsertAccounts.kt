package com.devamsba.managebudget.feat_accounts.domain.usecase

import com.devamsba.managebudget.feat_accounts.domain.entity.AccountsEntity
import com.devamsba.managebudget.feat_accounts.domain.repository.AccountsRepository
import javax.inject.Inject


class InsertAccounts @Inject constructor(private val repository: AccountsRepository) {

    suspend operator fun invoke(accounts: AccountsEntity) = repository.insert(accounts)

}