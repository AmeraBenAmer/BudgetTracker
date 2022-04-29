package com.devamsba.managebudget.common.domain.entities.purchases.entity

data class Items(

     val id: Int,
    var title :Int,
    var isChecked : Boolean = false,
    var price: Double,
    var currency : Int // dinar ly, dollar
)