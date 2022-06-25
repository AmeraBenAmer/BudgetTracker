package com.devamsba.managebudget.feat_history.domain.usecase

import com.devamsba.managebudget.feat_history.domain.entity.HistoryEntity
import com.devamsba.managebudget.feat_history.domain.repository.HistoryRepository
import javax.inject.Inject


class InsertHistory @Inject constructor(private val repository: HistoryRepository) {
    suspend operator fun invoke(history: HistoryEntity) = repository.insert(history)

}