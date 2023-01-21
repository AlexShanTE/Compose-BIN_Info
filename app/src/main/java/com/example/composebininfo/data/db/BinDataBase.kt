package com.example.composebininfo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composebininfo.domain.BinHistoryItem

@Database(entities = [BinHistoryItem::class], version = 1)
abstract class BinDataBase: RoomDatabase() {

    abstract val binDao:BinDao

    companion object {
        const val DATABASE_NAME = "history_db"
    }

}