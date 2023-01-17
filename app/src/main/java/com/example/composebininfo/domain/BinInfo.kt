package com.example.composebininfo.domain

data class BinInfo(
    val bank: Bank,
    val brand: String,
    val country: Country,
    val number: Number,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
)

data class Bank(
    val city: String,
    val name: String,
    val phone: String,
    val url: String
)

data class Country(
    val alpha2: String,
    val currency: String,
    val emoji: String,
    val latitude: Int,
    val longitude: Int,
    val name: String,
    val numeric: String
)

data class Number(
    val length: Int,
    val luhn: Boolean
)