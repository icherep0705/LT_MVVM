package com.example.up.lt_mvvm_.details

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.up.lt_mvvm_.data.Currency
import com.example.up.lt_mvvm_.data.db.ExchangeRate
import kotlinx.coroutines.launch
import kotlin.collections.MutableMap
import kotlin.collections.forEach
import kotlin.collections.isNotEmpty
import kotlin.collections.mutableMapOf
import kotlin.collections.set

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

//    fun getDBRates(ctx: Context) = liveData(viewModelScope.coroutineContext) {
//            emit(
//                kotlin.runCatching {
//                  //  CurrencyDatabase.getInstance(ctx)?.exchangeRateDao()?.getAll(currency)
//                })
//
//    }

    fun saveRatesDB(ctx: Context, currency: ExchangeRate) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                CurrencyDatabase.getInstance(ctx)?.exchangeRateDao()?.insertCurrency(currency)
            }
        }
    }

    fun getRatesDB(ctx: Context) = liveData(viewModelScope.coroutineContext) {
        withContext(Dispatchers.IO) {
            emit(
                    kotlin.runCatching {
                        CurrencyDatabase.getInstance(ctx)?.exchangeRateDao()?.getAll(currency)
                    })

        }
    }

    fun deleteRatesDB(ctx: Context) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                CurrencyDatabase.getInstance(ctx)?.exchangeRateDao()?.deleteAll(currency)
            }
        }
    }

    companion object {
        private val TAG = ListFragmentViewModel::class.java.simpleName
    }
}