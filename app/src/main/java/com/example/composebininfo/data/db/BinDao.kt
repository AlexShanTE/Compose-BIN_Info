package com.example.composebininfo.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composebininfo.domain.models.BinHistoryItem
import kotlinx.coroutines.flow.Flow

@Dao
interface BinDao {

    @Query("SELECT * FROM bin ORDER BY id DESC")
    fun getHistory(): Flow<List<BinHistoryItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBin(binHistoryItem: BinHistoryItem)

    @Delete
    suspend fun deleteBin(binHistoryItem: BinHistoryItem)

}