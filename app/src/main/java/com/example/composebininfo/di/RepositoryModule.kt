package com.example.composebininfo.di

import com.example.composebininfo.data.BinApi
import com.example.composebininfo.data.BinRepositoryImpl
import com.example.composebininfo.data.HistoryRepositoryImpl
import com.example.composebininfo.data.db.BinDataBase
import com.example.composebininfo.domain.BinRepository
import com.example.composebininfo.domain.HistoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideBinRepository(
        binApi: BinApi
    ): BinRepository {
        return BinRepositoryImpl(binApi)
    }

    @Provides
    @Singleton
    fun provideHistoryRepository(
        dataBase: BinDataBase
    ): HistoryRepository {
        return HistoryRepositoryImpl(dataBase.binDao)
    }
}