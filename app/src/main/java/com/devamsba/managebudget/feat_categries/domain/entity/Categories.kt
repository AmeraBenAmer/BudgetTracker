package com.devamsba.managebudget.feat_categries.domain.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.devamsba.managebudget.common.data.BaseEntity

@Entity(tableName = "category_tbl" , indices = [Index(value = ["id"], unique = true )] )
data class CategoriesEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    val name: String,
    val type: String
):BaseEntity