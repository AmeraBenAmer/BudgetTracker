package com.devamsba.managebudget.feat_incoms.domain.entity

data class TotalIncomesByCategory(
    var id_fk_category: String,
    var totalAmount: Double
)