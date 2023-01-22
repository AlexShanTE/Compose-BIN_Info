package com.example.composebininfo.domain.models

data class Bin(
    val bank: Bank? = null,
    val brand: String? = null,
    val country: Country? = null,
    val number: Number? = null,
    val prepaid: Boolean? = null,
    val scheme: String? = null,
    val type: String? = null
)

data class Bank(
    val city: String?,
    val name: String?,
    val phone: String?,
    val url: String?
)

data class Country(
    val alpha2: String?,
    val currency: String?,
    val emoji: String?,
    val latitude: Double?,
    val longitude: Double?,
    val name: String?,
    val numeric: String?
)

data class Number(
    val length: Int?,
    val luhn: Boolean?
)