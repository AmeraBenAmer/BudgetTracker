package com.devamsba.managebudget.feat_categries.data.local

import androidx.room.Dao
import androidx.room.Query
import com.devamsba.managebudget.common.data.BaseDao
import com.devamsba.managebudget.feat_categries.domain.entity.CategoriesEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoriesDao : BaseDao<CategoriesEntity> {
    @Query("SELECT * FROM category_tbl WHERE type = :type")
    fun fetchAllCategories(type: String): Flow<List<CategoriesEntity>>
}