package com.example.composebininfo.domain

interface BinRepository {

    suspend fun getInfo(bin: String) : BinInfo

}