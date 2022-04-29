package com.devamsba.managebudget.domain.repository

import com.devamsba.managebudget.common.data.BaseEntity
import com.devamsba.managebudget.common.data.income.entity.IncomeEntity
import kotlinx.coroutines.flow.Flow

interface Repository{
    suspend fun insert(entity: BaseEntity)
    suspend fun insertAll(entity: ArrayList<BaseEntity>)
    suspend fun update(entity: IncomeEntity)
    suspend fun delete(entity: IncomeEntity)
    fun getAll(): Flow<List<IncomeEntity>>

}