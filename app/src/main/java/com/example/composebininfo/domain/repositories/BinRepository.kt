package com.example.composebininfo.domain.repositories

import com.example.composebininfo.domain.models.Bin

interface BinRepository {

    suspend fun getInfo(bin: String) : Bin

}