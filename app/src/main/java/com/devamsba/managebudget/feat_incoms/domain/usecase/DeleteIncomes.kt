package com.devamsba.managebudget.feat_incoms.domain.usecase

import com.devamsba.managebudget.feat_incoms.domain.repository.IncomesRepository
import com.devamsba.managebudget.feat_incoms.domain.entity.IncomeEntity
import javax.inject.Inject

class DeleteIncomes @Inject constructor(private val repository: IncomesRepository) {

    suspend operator fun invoke(income: IncomeEntity) = repository.delete(income)

}

