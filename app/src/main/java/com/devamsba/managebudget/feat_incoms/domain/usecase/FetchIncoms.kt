package com.devamsba.managebudget.feat_incoms.domain.usecase

import com.devamsba.managebudget.feat_incoms.domain.entity.IncomeEntity
import com.devamsba.managebudget.feat_incoms.domain.entity.TotalIncomesByCategory
import com.devamsba.managebudget.feat_incoms.domain.repository.IncomesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchIncomes @Inject constructor(private val repository: IncomesRepository) {

    fun fetchAllIncomes(): Flow<List<IncomeEntity>> = repository.fetchAllIncome()
    fun fetchTotalAmount(): Flow<Double?> = repository.getTotalAmount()
    fun fetchTotalAmountGroupedBy(): Flow<List<TotalIncomesByCategory>> =
        repository.getTotalAmountGroupedByCategory()

}