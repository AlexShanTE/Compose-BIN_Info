package com.example.composebininfo.domain

interface BinRepository {
    fun getInfo(bin: String) : BinInfo

}