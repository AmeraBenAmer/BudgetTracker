package com.devamsba.managebudget.feat_history.data.local

import androidx.room.*
import com.devamsba.managebudget.common.data.BaseDao
import com.devamsba.managebudget.common.data.history.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class HistoryDao : BaseDao<HistoryEntity> {

    @Query("SELECT * FROM history")
    abstract fun getAllHistory(): Flow<List<HistoryEntity>>

}