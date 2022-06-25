package com.devamsba.managebudget.feat_expense.domain.entity

data class TotalExpenseByCategory(
    var id_fk_category: String,
    var totalAmount: Double
)