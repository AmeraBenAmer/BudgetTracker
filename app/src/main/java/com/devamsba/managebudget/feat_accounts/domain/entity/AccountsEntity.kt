package com.devamsba.managebudget.feat_accounts.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devamsba.managebudget.common.data.BaseEntity


@Entity(tableName = "account_tbl" )
data class AccountsEntity (
    @PrimaryKey(autoGenerate = true)
    override var id: Int = 0,
    val name: String
) :BaseEntity