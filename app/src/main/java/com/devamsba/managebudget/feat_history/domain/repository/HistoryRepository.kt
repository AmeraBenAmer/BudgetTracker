package com.devamsba.managebudget.feat_history.domain.repository

import com.devamsba.managebudget.feat_history.domain.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    suspend fun insert(history: HistoryEntity)
    suspend fun update(history: HistoryEntity)
    fun fetchAllHistory(): Flow<List<HistoryEntity>>
    fun filterHistoryByDate(month: Int, year: Int): Flow<List<HistoryEntity>>


}