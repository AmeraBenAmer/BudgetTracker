package com.devamsba.managebudget.feat_accounts.data.local

import androidx.room.Dao
import androidx.room.Query
import com.devamsba.managebudget.common.data.BaseDao
import com.devamsba.managebudget.feat_accounts.domain.entity.AccountsEntity


@Dao
abstract class AccountsDao : BaseDao<AccountsEntity>{


    @Query("SELECT * FROM ")
}