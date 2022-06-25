package com.devamsba.managebudget.feat_accounts.data.local

import androidx.room.Dao
import androidx.room.Query
import com.devamsba.managebudget.common.data.BaseDao
import com.devamsba.managebudget.feat_accounts.domain.entity.AccountsEntity
import kotlinx.coroutines.flow.Flow


@Dao
abstract class AccountsDao : BaseDao<AccountsEntity>{


    @Query("SELECT * FROM account_tbl ")
    abstract fun fetchAccounts(): Flow<List<AccountsEntity>>

}