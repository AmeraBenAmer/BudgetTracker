package com.devamsba.managebudget.feat_history.data.local

import androidx.room.*
import com.devamsba.managebudget.common.data.BaseDao
import com.devamsba.managebudget.feat_history.domain.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class HistoryDao : BaseDao<HistoryEntity> {

    @Query("SELECT * FROM history_tbl")
    abstract fun fetchAllHistory(): Flow<List<HistoryEntity>>

    @Query("SELECT * FROM history_tbl WHERE month =:month AND year=:year")
    abstract fun filterHistoryByDate(month: Int, year: Int): Flow<List<HistoryEntity>>

}