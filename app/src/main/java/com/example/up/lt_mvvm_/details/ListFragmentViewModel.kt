package com.example.up.lt_mvvm_.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.up.lt_mvvm_.data.ApiClient
import com.example.up.lt_mvvm_.data.Client
import com.example.up.lt_mvvm_.data.Currency
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragmentViewModel(private val currency: String): ViewModel() {

    init {
        Log.d(TAG, "ViewModel: $currency")
    }

    private val service = Client().createClient(ApiClient::class.java)

    fun getLiveRates() = liveData(viewModelScope.coroutineContext) {
        emit(
            kotlin.runCatching {
                service.getRepos(currency).await()
            })
    }
//    fun getLiveRates() {
//        service.getRepos(currency).enqueue(object : Callback<Currency> {
//            override fun onResponse(call: Call<Currency>, response: Response<Currency>) {
//                response.raw()
//            }
//
//            override fun onFailure(call: Call<Currency>, t: Throwable) {
//                t.message
//            }
//
//        })
//    }

    companion object {
        private val TAG = ListFragmentViewModel::class.java.simpleName
    }
}