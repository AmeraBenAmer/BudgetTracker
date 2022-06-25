package com.devamsba.managebudget.feat_expense.domain.usecase

data class ExpenseUseCase(
    val deleteExpense: DeleteExpense,
    val insertExpense: InsertExpense,
    val updateExpense: UpdateExpense,
    val fetchExpense: FetchExpense,
)