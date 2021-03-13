package com.example.up.lt_mvvm_.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

enum class Currencies {
    USD,EUR, GBP, JPY
}

@Parcelize
data class Currency(
    val rates: Map<String, Double>?,
    val base: String,
    val date: String
) : Parcelable
