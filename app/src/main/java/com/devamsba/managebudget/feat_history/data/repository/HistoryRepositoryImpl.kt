package com.devamsba.managebudget.feat_history.data.repository

import com.devamsba.managebudget.common.data.AppDatabase
import com.devamsba.managebudget.feat_history.domain.entity.HistoryEntity
import com.devamsba.managebudget.feat_history.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(private val db: AppDatabase) : HistoryRepository {
    override suspend fun insert(history: HistoryEntity) = db.historyDao().insert(history)

    override suspend fun update(history: HistoryEntity) = db.historyDao().update(history)

    override fun fetchAllHistory() = db.historyDao().fetchAllHistory()
    override fun filterHistoryByDate(month: Int, year: Int): Flow<List<HistoryEntity>>  = db.historyDao().filterHistoryByDate(month, year)


}