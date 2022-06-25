package com.devamsba.managebudget.feat_incoms.data.module

import com.devamsba.managebudget.common.data.AppDatabase
import com.devamsba.managebudget.feat_incoms.data.local.IncomeDao
import com.devamsba.managebudget.feat_incoms.data.repository.IncomesRepositoryImpl
import com.devamsba.managebudget.feat_incoms.domain.repository.IncomesRepository
import com.devamsba.managebudget.feat_incoms.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object IncomeModule {

    @Provides
    @Singleton
    fun provideIncomeDao(appDatabase: AppDatabase): IncomeDao {
        return appDatabase.incomeDao()
    }


    @Provides
    @Singleton
    fun provideIncomesUseCase(repository: IncomesRepository): IncomesUseCase {
        return IncomesUseCase(
            insertIncomes = InsertIncomes(repository),
            updateIncomes = UpdateIncomes(repository),
            deleteIncomes = DeleteIncomes(repository),
            FetchIncomes = FetchIncomes(repository)

        )
    }

    @Provides
    @Singleton
    fun provideRepository(appDataBase: AppDatabase): IncomesRepository {
        return IncomesRepositoryImpl(appDataBase)
    }
}
