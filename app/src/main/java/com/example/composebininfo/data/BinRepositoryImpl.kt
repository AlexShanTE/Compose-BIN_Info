package com.example.composebininfo.data

import com.example.composebininfo.domain.models.Bin
import com.example.composebininfo.domain.repositories.BinRepository

class BinRepositoryImpl(
    private val binApi: BinApi
): BinRepository {

    override suspend fun getInfo(bin: String): Bin {
        println("BinInforepo getinfo thread ${Thread.currentThread().name}")
        return binApi.getInfoByBin(bin = bin)
    }

}