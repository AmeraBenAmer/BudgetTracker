package com.devamsba.managebudget.feat_depts.domain.entity

data class Debts(
    val id : Int,
    val title: String,
    val nameOfDebtor: String,
    val dateOfDebt: String,
    val dateOfDebtReturn: String,
    val collectionOfDebtValue: String,// totally, partly
    val amountPartly: Double,
    val amount: Double,
    val isNotify: String,
)