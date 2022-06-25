package com.devamsba.managebudget.feat_categries.domain.usecase

data class CategoryUseCase(
    var insertCategory: InsertCategory,
    var deleteCategory: DeleteCategory,
    var updateCategory: UpdateCategory,
    var fetchCategory: FetchCategory
)