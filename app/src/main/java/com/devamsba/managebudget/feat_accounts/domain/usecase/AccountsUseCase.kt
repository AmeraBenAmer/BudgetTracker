package com.devamsba.managebudget.feat_accounts.domain.usecase


data class AccountsUseCase (
        var insertAccounts: InsertAccounts,
        var updateAccounts: UpdateAccounts,
        var deleteAccounts: DeleteAccounts,
        var fetchAccounts: FetchAccounts
        )