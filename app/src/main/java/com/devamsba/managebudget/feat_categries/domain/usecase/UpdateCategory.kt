package com.devamsba.managebudget.feat_categries.domain.usecase

import com.devamsba.managebudget.feat_categries.data.repository.CategoryRepositoryImpl
import com.devamsba.managebudget.feat_categries.domain.entity.CategoriesEntity
import com.devamsba.managebudget.feat_categries.domain.repository.CategoryRepository
import javax.inject.Inject

class UpdateCategory @Inject constructor(private val repository: CategoryRepository) {

    suspend operator fun invoke(category: CategoriesEntity) = repository.update(category)

}