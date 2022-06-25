package com.devamsba.managebudget.feat_depts_credit.data.module

import com.devamsba.managebudget.common.data.AppDatabase
import com.devamsba.managebudget.feat_history.data.local.HistoryDao
import com.devamsba.managebudget.feat_history.data.repository.HistoryRepositoryImpl
import com.devamsba.managebudget.feat_history.domain.repository.HistoryRepository
import com.devamsba.managebudget.feat_history.domain.usecase.FetchHistory
import com.devamsba.managebudget.feat_history.domain.usecase.HistoryUseCase
import com.devamsba.managebudget.feat_history.domain.usecase.InsertHistory
import com.devamsba.managebudget.feat_history.domain.usecase.UpdateHistory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HistoryModule {

    @Provides
    @Singleton
    fun provideHistoryDao(appDatabase: AppDatabase): HistoryDao {
        return appDatabase.historyDao()
    }


    @Provides
    @Singleton
    fun provideHistoryUseCase(repository: HistoryRepository): HistoryUseCase {
        return HistoryUseCase(
            insertHistory = InsertHistory(repository),
            updateHistory = UpdateHistory(repository),
            fetchHistory = FetchHistory(repository)
        )
    }

    @Provides
    @Singleton
    fun provideRepository(appDatabase: AppDatabase): HistoryRepository {
        return HistoryRepositoryImpl(appDatabase)
    }
}