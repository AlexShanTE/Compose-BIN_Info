package com.example.composebininfo.domain

import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun getHistory() : Flow<List<BinHistoryItem>>

    suspend fun insert(binHistoryItem: BinHistoryItem)

    suspend fun delete(binHistoryItem: BinHistoryItem)

}