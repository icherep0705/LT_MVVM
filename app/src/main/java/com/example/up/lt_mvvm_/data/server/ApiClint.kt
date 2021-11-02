package com.example.up.lt_mvvm_.data

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiClient {

    //GET https://api.exchangeratesapi.io/latest?base=USD
    //GET https://api.exchangeratesapi.io/latest?base=USD

    @GET("latest")
//    @GET("latest")
    fun getRates(
        @Query("base") currency: String?): Deferred<Currency>
}