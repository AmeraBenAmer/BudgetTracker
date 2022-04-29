package com.devamsba.managebudget.common.domain.entities.purchases.entity

data class Purchases(

     val id: Int,
    var title :Int,
    var date : String,
    var isNotify : Boolean,
    var totalAmount: Double,
    var currency : Int // dinar ly, dollar
)