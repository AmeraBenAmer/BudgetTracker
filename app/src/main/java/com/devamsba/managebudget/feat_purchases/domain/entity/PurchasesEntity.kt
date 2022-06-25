package com.devamsba.managebudget.feat_purchases.domain.entity

import androidx.room.*
import com.devamsba.managebudget.common.data.BaseEntity

@Entity(tableName = "purchases_tbl", indices = [Index(value = ["id"], unique = true)])
data class PurchasesEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Int,
    @ColumnInfo(name = "purchases_item_id")
    val purchasesItemId: Int,
    @ColumnInfo(name = "date_time")
    var dateTime: String,
    @ColumnInfo(name = "category")
    var category: String,
    @ColumnInfo(name = "total_amount")
    var totalAmount: Double,
    @ColumnInfo(name = "account")
    var account: String,
//    @Embedded
//    var items: List<ItemDetails>,
    @ColumnInfo(name = "currency")
    var currency: Int // dinar ly, dollar
) : BaseEntity

@Entity(tableName = "item_details_tbl", indices = [Index(value = ["id"], unique = true)])
data class ItemDetails(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name_item")
    var nameItem: String,
    @ColumnInfo(name = "amount_item")
    var amountItem: Double,
    @ColumnInfo(name = "quantity_items")
    var quantityItems: Double,
    @ColumnInfo(name = "is_purchase_completed")
    var isPurchaseCompleted: Boolean,
    @ColumnInfo(name = "unit_quantity")
    var unitOfQuantity: String // "kg" , "pieces"
)


data class PurchasesWithItems(
    @Embedded val purchases: PurchasesEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "purchasesItemId"
    )
    val items: List<ItemDetails>
)
