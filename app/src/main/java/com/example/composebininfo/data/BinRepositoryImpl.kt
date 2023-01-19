package com.example.composebininfo.data

import com.example.composebininfo.domain.BinInfoModel
import com.example.composebininfo.domain.BinRepository

class BinRepositoryImpl(
    private val binApi: BinApi
): BinRepository {

    override suspend fun getInfo(bin: String): BinInfoModel {
        return binApi.getInfoByBin(bin = bin)
    }

}