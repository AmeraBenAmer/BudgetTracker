package com.devamsba.managebudget.feat_categries.domain.usecase

import com.devamsba.managebudget.feat_categries.data.repository.CategoryRepositoryImpl
import com.devamsba.managebudget.feat_categries.domain.entity.CategoriesEntity
import com.devamsba.managebudget.feat_categries.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class InsertCategory @Inject constructor(private val repository: CategoryRepository) {

    suspend operator fun invoke(category: CategoriesEntity) = repository.insert(category)
    suspend operator fun invoke(category: ArrayList<CategoriesEntity>) = repository.insertAll(category)

}