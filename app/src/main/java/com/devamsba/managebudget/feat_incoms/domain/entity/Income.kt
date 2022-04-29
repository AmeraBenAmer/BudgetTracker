package com.devamsba.managebudget.feat_incoms.domain.entity

import com.devamsba.managebudget.common.domain.entities.Entity

data class Income(

    val id: Int,
    var title: Int,
    var date: String,
    var isNotify: Boolean,
    var amount: Double,
    var currency: Int // dinar ly, dollar
) : Entity {



}