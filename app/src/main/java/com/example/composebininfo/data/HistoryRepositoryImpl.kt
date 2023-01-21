package com.example.composebininfo.data

import com.example.composebininfo.data.db.BinDao
import com.example.composebininfo.domain.BinHistoryItem
import com.example.composebininfo.domain.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val dao: BinDao
) : HistoryRepository {

    override fun getHistory(): Flow<List<BinHistoryItem>> {
        return dao.getHistory()
    }

    override suspend fun insert(binHistoryItem: BinHistoryItem) {
        dao.insertBin(binHistoryItem)
    }

    override suspend fun delete(binHistoryItem: BinHistoryItem) {
        dao.deleteBin(binHistoryItem)
    }
}