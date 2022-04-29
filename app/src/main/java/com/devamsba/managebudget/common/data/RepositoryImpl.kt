package com.devamsba.managebudget.common.data

import android.util.Log
import com.devamsba.managebudget.common.data.income.entity.IncomeEntity
import com.devamsba.managebudget.common.data.typeMoney.entity.TypeMoneyEntity
import com.devamsba.managebudget.feat_depts.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val db: AppDatabase,
): Repository {


    override suspend fun insert(entity: BaseEntity) {
        Log.e("insertData", "insertData: here 345", )

        when(entity){
            is IncomeEntity ->  db.incomeDao().insert(entity)
            is TypeMoneyEntity ->  db.typeMoneyDao().insert(entity)
        }

    }

    override suspend fun insertAll(entity: ArrayList<BaseEntity>) {
        when(entity.firstOrNull()){
            is TypeMoneyEntity ->  db.typeMoneyDao().insertAll(entity as ArrayList<TypeMoneyEntity>)
        }
    }


    override suspend fun update(entity: IncomeEntity) =db.incomeDao().update(entity)

    override suspend fun delete(entity: IncomeEntity) = db.incomeDao().delete(entity)
    override fun getAll(): Flow<List<IncomeEntity>> {
        return db.incomeDao().getAllIncome()
    }
}