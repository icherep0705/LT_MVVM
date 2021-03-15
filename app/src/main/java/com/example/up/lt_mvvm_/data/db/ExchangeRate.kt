package com.example.up.lt_mvvm_.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ExchangeRate")
data class ExchangeRate(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "base")
    val baseCurrency: String = "",

    @ColumnInfo(name = "currency")
    var currency: String = "",

    @ColumnInfo(name = "exchange_rate")
    var exchangeRate: Double = 0.0,

    @ColumnInfo(name = "time_stamp")
    var timeStamp: String = ""
)