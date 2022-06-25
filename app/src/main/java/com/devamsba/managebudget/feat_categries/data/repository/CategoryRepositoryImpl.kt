package com.devamsba.managebudget.feat_categries.data.repository

import com.devamsba.managebudget.common.data.AppDatabase
import com.devamsba.managebudget.feat_categries.domain.entity.CategoriesEntity
import com.devamsba.managebudget.feat_categries.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val db: AppDatabase
) : CategoryRepository {
    override suspend fun insert(category: CategoriesEntity) = db.categoryDao().insert(category)
    override suspend fun insertAll(categories: ArrayList<CategoriesEntity>) =
        db.categoryDao().insertAll(categories)

    override suspend fun update(categories: CategoriesEntity) = db.categoryDao().update(categories)
    override suspend fun delete(categories: CategoriesEntity) = db.categoryDao().delete(categories)

    override fun fetchAllCategories(type: String): Flow<List<CategoriesEntity>> =
        db.categoryDao().fetchAllCategories(type)


}