package com.devamsba.managebudget.feat_accounts.data.module

import com.devamsba.managebudget.common.data.AppDatabase
import com.devamsba.managebudget.feat_accounts.data.local.AccountsDao
import com.devamsba.managebudget.feat_accounts.data.repository.AccountsRepository
import com.devamsba.managebudget.feat_accounts.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AccountsModule {

    @Provides
    @Singleton
    fun provideTypeMoneyDao(appDatabase: AppDatabase): AccountsDao {
        return appDatabase.accountsDao()
    }

    @Provides
    @Singleton
    fun provideAccountUseCase(repository: AccountsRepository): AccountsUseCase{
        return AccountsUseCase(
            fetchAccounts = FetchAccounts(repository),
            updateAccounts = UpdateAccounts(repository),
            deleteAccounts = DeleteAccounts(repository),
            insertAccounts = InsertAccounts(repository)

        )
    }
}