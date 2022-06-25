package com.devamsba.managebudget.feat_incoms.domain.usecase

import com.devamsba.managebudget.feat_incoms.domain.entity.IncomeEntity
import com.devamsba.managebudget.feat_incoms.domain.repository.IncomesRepository
import javax.inject.Inject


class InsertIncomes @Inject constructor(private val repository: IncomesRepository) {

    suspend operator fun invoke(income: IncomeEntity) = repository.insert(income)

}