package com.devamsba.managebudget.feat_depts.data.local

import androidx.room.*
import com.devamsba.managebudget.common.data.BaseDao
import com.devamsba.managebudget.common.data.debts.entity.DebtsEntity
import kotlinx.coroutines.flow.Flow


@Dao
abstract class DebtsDao: BaseDao<DebtsEntity> {

    @Query("SELECT * FROM debts")
    abstract fun getAllDebts(): Flow<List<DebtsEntity>>

}