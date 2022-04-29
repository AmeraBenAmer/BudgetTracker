package com.devamsba.managebudget.domain.usecase

import android.util.Log
import com.devamsba.managebudget.common.data.BaseEntity
import com.devamsba.managebudget.common.data.typeMoney.entity.TypeMoneyEntity
import com.devamsba.managebudget.feat_depts.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
//
class InsertUseCase @Inject constructor(private val repository: Repository): BaseUseCase{


    override suspend fun invokeAll(listOfEntity: ArrayList<BaseEntity>) {
        when(listOfEntity.firstOrNull()){
            is TypeMoneyEntity ->  repository.insertAll(entity = listOfEntity)
        }
    }
    override suspend fun invoke(entity: BaseEntity) {
        Log.e("insertData", "insertData: here 34 ", )

//        when(entity){
              repository.insert(entity = entity)
//            is IncomeEntity ->  repository.insert(entity = entity)
//            is TypeMoneyEntity ->  repository.insert(entity = entity)
//        }

    }

    override suspend fun invokeGet(): Flow<List<BaseEntity>> {
        TODO("Not yet implemented")
    }


}