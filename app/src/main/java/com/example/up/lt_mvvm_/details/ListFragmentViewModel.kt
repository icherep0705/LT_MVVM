package com.example.up.lt_mvvm_.details

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.up.lt_mvvm_.data.Currency
import com.example.up.lt_mvvm_.data.db.ExchangeRate
import kotlinx.coroutines.launch
import kotlin.collections.set

class ListFragmentViewModel(
        private val currency: String,
        private val isConnected: Boolean,
        application: Application,
        private  val serverRepo: Repository
): AndroidViewModel(application) {

    val currencyLiveData : MutableLiveData<Currency> by lazy {  MutableLiveData<Currency>() }
    val errorLiveData : MutableLiveData<String> by lazy {  MutableLiveData<String>() }
    private val ctx: Application = application

    init {
        if (isConnected) {
            getLiveRates()
        } else {
            getRatesDB(ctx)
        }
    }

    private fun getLiveRates(){
        viewModelScope.launch {
            val liveRates = serverRepo.getLiveRates(currency)
            liveRates.observeForever{ result ->
                result.fold(
                        onSuccess = {
                            currencyLiveData.postValue(it)
                            deleteRatesDB()

                            it.rates?.keys?.forEach{ s ->
                                val currency = ExchangeRate(
                                        baseCurrency = it.base,
                                        timeStamp = it.date,
                                        currency = s,
                                        exchangeRate = it.rates?.get(s) ?: 0.0
                                )
                                saveRatesDB(currency)
                            }
                        },

                        onFailure = {
                            errorLiveData.postValue("No Data Available")
                        }
                )
            }
        }
    }

    private fun saveRatesDB(currency: ExchangeRate) {
        viewModelScope.launch {
            serverRepo.saveRatesDB(ctx, currency)
        }
    }

    private fun deleteRatesDB() {
        viewModelScope.launch {
            serverRepo.deleteRatesDB(ctx, currency)
        }
    }

    private fun getRatesDB(ctx: Context) {

        viewModelScope.launch {
            val liveRates = serverRepo.getRatesDB(ctx, currency)
            liveRates.observeForever{ result: Result<List<ExchangeRate>?> ->
                result.fold(
                        onSuccess = { rates ->

                            rates?.let {
                                if (rates.isNotEmpty()) {

                                    val data: MutableMap<String, Double> = mutableMapOf()
                                    rates.forEach {
                                        data[it.currency] = it.exchangeRate
                                    }
                                    val exchangeRate = Currency(
                                            date = rates[0].timeStamp,
                                            base = rates[0].baseCurrency,
                                            rates = data
                                    )
                                    currencyLiveData.postValue(exchangeRate)

                                } else {
                                    errorLiveData.postValue("No Data Available")
                                }
                            }
                        },

                        onFailure = {
                            errorLiveData.postValue("No Data Available")
                        }
                )
            }
        }
    }

    companion object {
        private val TAG = ListFragmentViewModel::class.java.simpleName
    }
}