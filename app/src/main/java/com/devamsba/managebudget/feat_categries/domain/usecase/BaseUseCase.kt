package com.devamsba.managebudget.domain.usecase

import com.devamsba.managebudget.common.data.BaseEntity
import kotlinx.coroutines.flow.Flow

interface BaseUseCase{
    suspend fun  invoke(entity: BaseEntity)
    suspend fun  invokeAll(listOfEntity: ArrayList<BaseEntity>)
    suspend fun  invokeGet() : Flow<List<BaseEntity>>
}