package com.example.up.lt_mvvm_.data

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiClient {

    //GET https://api.exchangeratesapi.io/latest?base=USD

    @GET("latest")
    fun getRepos(
        @Query("base") currency: String?): Deferred<Currency>
}