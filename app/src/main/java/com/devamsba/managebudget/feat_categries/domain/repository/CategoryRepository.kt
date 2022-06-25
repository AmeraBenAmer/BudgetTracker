package com.devamsba.managebudget.feat_categries.domain.repository

import com.devamsba.managebudget.feat_categries.domain.entity.CategoriesEntity
import kotlinx.coroutines.flow.Flow

interface CategoryRepository{
    suspend fun insert(category: CategoriesEntity)
    suspend fun insertAll(categories: ArrayList<CategoriesEntity>)
    suspend fun update(categories: CategoriesEntity)
    suspend fun delete(categories: CategoriesEntity)
    fun fetchAllCategories(type: String): Flow<List<CategoriesEntity>>

}