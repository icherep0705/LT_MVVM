package com.example.up.lt_mvvm_.details

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.up.lt_mvvm_.data.ApiClient
import com.example.up.lt_mvvm_.data.db.CurrencyDatabase
import com.example.up.lt_mvvm_.data.db.ExchangeRate
import com.example.up.lt_mvvm_.data.server.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListFragmentViewModel(private val currency: String): ViewModel() {

    init {
        Log.d(TAG, "ViewModel: $currency")
    }

    private val service = Client().createClient(ApiClient::class.java)

    fun getLiveRates() = liveData(viewModelScope.coroutineContext) {
        emit(
            kotlin.runCatching {
                service.getRates(currency).await()
            })
    }

    fun getDBRates(ctx: Context) = liveData(viewModelScope.coroutineContext) {
            emit(
                kotlin.runCatching {
                  //  CurrencyDatabase.getInstance(ctx)?.exchangeRateDao()?.getAll(currency)
                })

    }

    fun saveRates(ctx: Context, currency: ExchangeRate) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                CurrencyDatabase.getInstance(ctx)?.exchangeRateDao()?.insertCurrency(currency)
            }
        }
    }

    companion object {
        private val TAG = ListFragmentViewModel::class.java.simpleName
    }
}