package com.devamsba.managebudget.feat_history.domain.usecase

data class HistoryUseCase(
    var updateHistory: UpdateHistory,
    var insertHistory: InsertHistory,
    var fetchHistory: FetchHistory
)