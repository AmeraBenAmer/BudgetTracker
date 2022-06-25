package com.devamsba.managebudget.feat_incoms.domain.entity

import androidx.room.*
import com.devamsba.managebudget.common.data.BaseEntity
import com.devamsba.managebudget.feat_categries.domain.entity.CategoriesEntity
import java.time.ZonedDateTime

@Entity(
    tableName = "income_tbl",
    indices = [Index(value = ["id"], unique = true)],
//    foreignKeys = [ForeignKey(
//        entity = CategoriesEntity::class,
//        parentColumns = ["id"],
//        childColumns = ["id_fk_category"],
//        onDelete = ForeignKey.CASCADE
//    )]
)
data class IncomeEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "date")
    var date: String,
//    @ColumnInfo(name = "data_as_zone")
//    var dateAsZone : ZonedDateTime,
    @ColumnInfo(name = "is_notify")
    var isNotify: Boolean,
    @ColumnInfo(name = "amount")
    var amount: Double,
    @ColumnInfo(name = "currency")
    var currency: String, // dinar ly
    @ColumnInfo(name = "id_fk_category")
    var idFKCategory: String,
    @ColumnInfo(name = "account_name")
    var accountName: String

) : BaseEntity

