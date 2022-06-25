package com.devamsba.managebudget.feat_history.domain.usecase

import com.devamsba.managebudget.feat_history.domain.entity.HistoryEntity
import com.devamsba.managebudget.feat_history.domain.repository.HistoryRepository
import javax.inject.Inject

class UpdateHistory @Inject constructor(private val repository: HistoryRepository) {
    suspend operator fun invoke(historyEntity: HistoryEntity) = repository.update(historyEntity)
}