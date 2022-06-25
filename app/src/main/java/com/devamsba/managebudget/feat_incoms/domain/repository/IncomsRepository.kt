package com.devamsba.managebudget.feat_incoms.domain.repository

import com.devamsba.managebudget.feat_incoms.domain.entity.IncomeEntity
import com.devamsba.managebudget.feat_incoms.domain.entity.TotalIncomesByCategory
import kotlinx.coroutines.flow.Flow

interface IncomesRepository {


    suspend fun insert(income: IncomeEntity)
    suspend fun insertAll(incomes: ArrayList<IncomeEntity>)
    suspend fun update(income: IncomeEntity)
    suspend fun delete(income: IncomeEntity)
    fun fetchAllIncome(): Flow<List<IncomeEntity>>
    fun getTotalAmount(): Flow<Double>
    fun getTotalAmountGroupedByCategory() : Flow<List<TotalIncomesByCategory>>

}