package com.example.up.lt_mvvm_.details

import android.content.Context
import androidx.lifecycle.liveData
import com.example.up.lt_mvvm_.data.ApiClient
import com.example.up.lt_mvvm_.data.db.CurrencyDatabase
import com.example.up.lt_mvvm_.data.db.ExchangeRate
import com.example.up.lt_mvvm_.data.server.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository {

    private val service = Client().createClient(ApiClient::class.java)

    suspend fun getLiveRates(base: String) = liveData(Dispatchers.IO) {
        emit(
                kotlin.runCatching {
                    service.getRates(base).await()
                })
    }

    suspend fun saveRatesDB(ctx: Context, currency: ExchangeRate) {
        withContext(Dispatchers.IO) {
            CurrencyDatabase.getInstance(ctx)?.exchangeRateDao()?.insertCurrency(currency)
        }
    }

    suspend fun deleteRatesDB(ctx: Context, base: String) {
        withContext(Dispatchers.IO) {
            CurrencyDatabase.getInstance(ctx)?.exchangeRateDao()?.deleteAll(base)
        }
    }

    suspend fun getRatesDB(ctx: Context, base: String) = liveData(Dispatchers.IO) {
        emit(
                kotlin.runCatching {
                    CurrencyDatabase.getInstance(ctx)?.exchangeRateDao()?.getAll(base)
                })
    }
}