package com.devamsba.managebudget.feat_depts_credit.data.local

import androidx.room.Dao
import androidx.room.Query
import com.devamsba.managebudget.common.data.BaseDao
import com.devamsba.managebudget.feat_depts_credit.domain.entity.CreditDebtsEntity
import kotlinx.coroutines.flow.Flow


@Dao
abstract class CreditDebtsDao: BaseDao<CreditDebtsEntity> {

    @Query("SELECT * FROM credit_debits_tbl")
    abstract fun getAllDebts(): Flow<List<CreditDebtsEntity>>

}