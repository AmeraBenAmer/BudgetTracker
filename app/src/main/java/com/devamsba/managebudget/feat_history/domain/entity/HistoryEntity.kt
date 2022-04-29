package com.devamsba.managebudget.feat_history.domain.entity

data class History(
     val id : Int,
    val actionTitle: String,
    val dateTime: String,
    val amount: Double,
    val type: String, // debit, income
)