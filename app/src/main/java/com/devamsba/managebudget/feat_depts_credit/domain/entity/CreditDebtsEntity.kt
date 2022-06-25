package com.devamsba.managebudget.feat_depts_credit.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.devamsba.managebudget.common.data.BaseEntity

@Entity(tableName = "credit_debits_tbl", indices = [Index(value = ["id"], unique = true)])
data class CreditDebtsEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "name_of_debtor_or_creditor")
    val nameOfDebtor: String,
    @ColumnInfo(name = "date_of_debt_or_credit")
    val dateOfDebt: String,
    @ColumnInfo(name = "date_of_debt_or_credit_return")
    val dateOfDebtReturn: String,
    @ColumnInfo(name = "collection_of_dept_or_credit_value")
    val collectionOfDebtValue: String,// totally, partly
    @ColumnInfo(name = "amount_partly")
    val amountPartly: Double,
    @ColumnInfo(name = "amount")
    val amount: Double,
    @ColumnInfo(name = "is_notify")
    val isNotify: String,
) : BaseEntity