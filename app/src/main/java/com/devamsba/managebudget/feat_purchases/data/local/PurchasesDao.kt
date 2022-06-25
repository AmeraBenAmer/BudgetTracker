package com.devamsba.managebudget.feat_purchases.data.local

import androidx.room.*
import com.devamsba.managebudget.common.data.BaseDao
import com.devamsba.managebudget.feat_purchases.domain.entity.PurchasesEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class  PurchasesDao : BaseDao<PurchasesEntity> {

    @Query("SELECT * FROM purchases_tbl")
    abstract fun getAllPurchases(): Flow<List<PurchasesEntity>>
}