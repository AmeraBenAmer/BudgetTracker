package com.devamsba.managebudget.feat_expense.domain.usecase

import com.devamsba.managebudget.feat_expense.domain.entity.ExpenseEntity
import com.devamsba.managebudget.feat_expense.domain.repository.ExpenseRepository
import javax.inject.Inject


class InsertExpense @Inject constructor(private val repository: ExpenseRepository) {
    suspend operator fun invoke(expense: ExpenseEntity) = repository.insert(expense)

}