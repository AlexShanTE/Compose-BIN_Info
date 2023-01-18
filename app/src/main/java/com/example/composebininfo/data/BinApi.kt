package com.example.composebininfo.data

import com.example.composebininfo.domain.BinInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApi {

    @GET("/{bin}")
    suspend fun getInfoByBin(@Path("bin") bin:String): BinInfo

}