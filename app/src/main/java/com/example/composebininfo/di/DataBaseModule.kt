package com.example.composebininfo.di

import android.content.Context
import androidx.room.Room
import com.example.composebininfo.data.db.BinDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideBinDataBase(@ApplicationContext appContext: Context): BinDataBase {
        return Room.databaseBuilder(
            appContext,
            BinDataBase::class.java,
            BinDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideBinDao(dataBase: BinDataBase) = dataBase.binDao
}