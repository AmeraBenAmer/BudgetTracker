package com.devamsba.managebudget.feat_history.domain.usecase

import com.devamsba.managebudget.feat_history.domain.repository.HistoryRepository
import javax.inject.Inject

class FetchHistory @Inject constructor(private val  repository: HistoryRepository) {
    suspend operator fun invoke() = repository.fetchAllHistory()
    suspend  fun filterHistoryByDate(month: Int, year: Int) = repository.filterHistoryByDate(month, year)

}