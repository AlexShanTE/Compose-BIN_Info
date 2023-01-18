package com.example.composebininfo.data

import com.example.composebininfo.domain.BinInfo
import com.example.composebininfo.domain.BinRepository

class BinRepositoryImpl(
    private val binApi: BinApi
): BinRepository {

    override suspend fun getInfo(bin: String): BinInfo {
        return binApi.getInfoByBin(bin = bin)
    }

}