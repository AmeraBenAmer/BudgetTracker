package com.devamsba.managebudget.common.data

import androidx.room.*


interface BaseDao<T: BaseEntity> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(t: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(t: ArrayList<T>)

    @Update
    suspend fun update(t: T)

    @Delete
    suspend fun delete(t: T)

}