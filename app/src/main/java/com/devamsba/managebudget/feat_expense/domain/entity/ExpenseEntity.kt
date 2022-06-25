package com.devamsba.managebudget.feat_expense.domain.entity

import androidx.room.*
import com.devamsba.managebudget.common.data.BaseEntity
import com.devamsba.managebudget.common.domain.entities.ZonedDateTimeTypeConverter
import com.devamsba.managebudget.feat_categries.domain.entity.CategoriesEntity
import java.time.ZonedDateTime

@Entity(
    tableName = "expense_tbl",
//    foreignKeys = [ForeignKey(
//        entity = CategoriesEntity::class,
//        parentColumns = ["id"],
//        childColumns = ["id_fk_category"],
//        onDelete = ForeignKey.CASCADE
//    )],
    indices = [Index(value = ["id"], unique = true)]
)
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "date_time")
    val dateTime: String,
//    @ColumnInfo(name = "data_as_zone")
//    var dateAsZone : ZonedDateTimeTypeConverter,
    @ColumnInfo(name = "amount")
    val amount: Double,
    @ColumnInfo(name = "is_notify")
    var isNotify: Boolean,
    @ColumnInfo(name = "currency")
    var currency: String, // dinar ly
    @ColumnInfo(name = "id_fk_category")
    var idFKCategory: String,
    @ColumnInfo(name = "account_name")
    var accountName: String

) : BaseEntity