package com.devamsba.managebudget.feat_history.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.devamsba.managebudget.common.data.BaseEntity

@Entity(tableName = "history_tbl", indices = [Index(value = ["id"], unique = true)])
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    @ColumnInfo(name = "action_title")
    val actionTitle: String,
    @ColumnInfo(name = "date_time")
    val dateTime: String,
    @ColumnInfo(name = "amount")
    val amount: Double,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "month")
    val month: Int,
    @ColumnInfo(name = "year")
    val year: Int,

    ) : BaseEntity
