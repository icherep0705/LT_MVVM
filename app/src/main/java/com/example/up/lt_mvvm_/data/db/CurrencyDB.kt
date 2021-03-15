package com.example.up.lt_mvvm_.data.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrencyDB(
    val baseCurrency: String = "",
    var currency: String = "",
    var exchangeRate: Double = 0.0,
    var timeStamp: String = ""
) : Parcelable
