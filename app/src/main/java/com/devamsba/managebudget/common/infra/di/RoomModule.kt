package com.devamsba.managebudget.common.infra.di

import android.content.Context
import androidx.room.Room
import com.devamsba.managebudget.common.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return  Room.databaseBuilder(context, AppDatabase::class.java, "save_money_db")
            .allowMainThreadQueries()
            .build()
    }


//    @Provides
//    @Singleton
//
//    fun provideBaseDao(appDataBase: AppDatabase): BaseDao<BaseEntity>{
//        return appDataBase.incomeDao()
//    }


}