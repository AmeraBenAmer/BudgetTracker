package com.devamsba.managebudget.feat_categries.data.module

import com.devamsba.managebudget.feat_categries.data.local.CategoriesDao
import com.devamsba.managebudget.common.data.AppDatabase
import com.devamsba.managebudget.feat_accounts.data.repository.AccountsRepositoryImpl
import com.devamsba.managebudget.feat_accounts.domain.repository.AccountsRepository
import com.devamsba.managebudget.feat_accounts.domain.usecase.*
import com.devamsba.managebudget.feat_categries.data.repository.CategoryRepositoryImpl
import com.devamsba.managebudget.feat_categries.domain.repository.CategoryRepository
import com.devamsba.managebudget.feat_categries.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CategoriesModule {

    @Provides
    @Singleton
    fun provideCategoriesDao(appDatabase: AppDatabase): CategoriesDao{
        return appDatabase.categoryDao()
    }

    @Provides
    @Singleton
    fun provideCategoriesUseCase(repository: CategoryRepository): CategoryUseCase {
        return CategoryUseCase(
            fetchCategory = FetchCategory(repository),
            updateCategory = UpdateCategory(repository),
            deleteCategory = DeleteCategory(repository),
            insertCategory = InsertCategory(repository)

        )
    }

    @Provides
    @Singleton
    fun  provideRepository(appDataBase: AppDatabase): CategoryRepository {
        return CategoryRepositoryImpl(appDataBase)
    }
}