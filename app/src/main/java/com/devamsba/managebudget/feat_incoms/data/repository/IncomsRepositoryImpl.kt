package com.devamsba.managebudget.feat_incoms.data.repository

import com.devamsba.managebudget.common.data.AppDatabase
import com.devamsba.managebudget.feat_incoms.domain.entity.IncomeEntity
import com.devamsba.managebudget.feat_incoms.domain.entity.TotalIncomesByCategory
import com.devamsba.managebudget.feat_incoms.domain.repository.IncomesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IncomesRepositoryImpl @Inject constructor(
    private val db: AppDatabase
) : IncomesRepository {
    override suspend fun insert(income: IncomeEntity) = db.incomeDao().insert(income)

    override suspend fun insertAll(incomes: ArrayList<IncomeEntity>) =
        db.incomeDao().insertAll(incomes)

    override suspend fun update(income: IncomeEntity) = db.incomeDao().update(income)

    override suspend fun delete(income: IncomeEntity) = db.incomeDao().delete(income)

    override fun fetchAllIncome(): Flow<List<IncomeEntity>> = db.incomeDao().getAllIncome()
    override fun getTotalAmount(): Flow<Double> = db.incomeDao().getTotalAmount()

    override fun getTotalAmountGroupedByCategory(): Flow<List<TotalIncomesByCategory>> =
        db.incomeDao().getTotalAmountGroupedByCategory()


}