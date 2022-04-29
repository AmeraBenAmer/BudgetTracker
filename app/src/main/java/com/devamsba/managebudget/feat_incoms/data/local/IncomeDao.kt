package com.devamsba.managebudget.feat_incoms.data.local

import androidx.room.*
import com.devamsba.managebudget.common.data.BaseDao
import com.devamsba.managebudget.common.data.income.entity.IncomeEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class  IncomeDao: BaseDao<IncomeEntity> {

    @Query("SELECT * FROM income")
    abstract fun getAllIncome():  Flow<List<IncomeEntity>>

}