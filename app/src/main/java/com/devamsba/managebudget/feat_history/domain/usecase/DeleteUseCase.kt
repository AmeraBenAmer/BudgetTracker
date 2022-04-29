package com.devamsba.managebudget.domain.usecase

import com.devamsba.managebudget.common.data.BaseEntity
import com.devamsba.managebudget.feat_depts.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteUseCase @Inject constructor(private val repository: Repository) :BaseUseCase{
    override suspend fun invoke(entity: BaseEntity) {
        TODO("Not yet implemented")
    }


    override suspend fun invokeGet(): Flow<List<BaseEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun invokeAll(listOfEntity: ArrayList<BaseEntity>) {
        TODO("Not yet implemented")
    }


}


