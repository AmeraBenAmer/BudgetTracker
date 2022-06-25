package com.devamsba.managebudget.feat_incoms.data.local

import androidx.room.Dao
import androidx.room.Query
import com.devamsba.managebudget.common.data.BaseDao
import com.devamsba.managebudget.feat_incoms.domain.entity.IncameByDate
import com.devamsba.managebudget.feat_incoms.domain.entity.IncomeEntity
import com.devamsba.managebudget.feat_incoms.domain.entity.TotalIncomesByCategory
import kotlinx.coroutines.flow.Flow

@Dao
abstract class IncomeDao : BaseDao<IncomeEntity> {

    @Query("SELECT * FROM income_tbl")
    abstract fun getAllIncome(): Flow<List<IncomeEntity>>


    @Query("SELECT id_fk_category, sum(amount) as totalAmount FROM income_tbl GROUP BY id_fk_category")
    abstract fun getTotalAmountGroupedByCategory(): Flow<List<TotalIncomesByCategory>>


    @Query("SELECT sum(amount) as totalAmount FROM income_tbl ")
    abstract fun getTotalAmount(): Flow<Double>

//    @Query("SELECT sum(amount) as totalAmount FROM income_tbl WHERE date between :date AND :date ")
//    abstract fun getIncameBasedOnDate(date: String): Flow<List<IncameByDate>>


}