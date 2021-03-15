package com.example.up.lt_mvvm_.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

enum class Currencies {
    USD,EUR, GBP, JPY
}

@Parcelize
data class Currency(
        var rates: Map<String, Double>?,
        var base: String,
        var date: String
) : Parcelable
