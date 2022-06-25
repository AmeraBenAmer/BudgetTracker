package com.devamsba.managebudget.feat_expense.domain.usecase

import com.devamsba.managebudget.feat_expense.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchExpense @Inject constructor(private val repository: ExpenseRepository) {

     fun fetchAllExpense() = repository.fetchAllExpense()
     fun fetchTotalAmountGroupedBy() = repository.getTotalAmountGroupedByCategory()
     fun fetchTotalAmount(): Flow<Double?> = repository.getTotalAmount()

}