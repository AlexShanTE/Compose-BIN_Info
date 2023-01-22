package com.example.composebininfo.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bin")
data class BinHistoryItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int = 0,
    @ColumnInfo(name = "bin")
    val bin : String = ""
)
