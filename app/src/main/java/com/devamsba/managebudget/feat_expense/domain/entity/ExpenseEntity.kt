package com.devamsba.managebudget.feat_expense.domain.entity

data class Expense(
    val id : Int,
    val title: String,
    val dateTime: String,
    val amount: Double,
)