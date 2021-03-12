package com.example.up.lt_mvvm_.model

import com.example.up.lt_mvvm_.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


private const val timeoutRead = 30
private const val contentType = "Content-Type"
private const val contentTypeValue = "application/json"
private const val timeoutConnect = 30
private const val BASE_URL = "TODO"

@Singleton
class Service @Inject constructor() {
    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private val retrofit: Retrofit

    private var headerInterceptor = Interceptor { chain ->
        val original = chain.request()

        val request = original.newBuilder()
                .header(contentType, contentTypeValue)
                .method(original.method, original.body)
                .build()

        chain.proceed(request)
    }

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }
            return loggingInterceptor
        }

    init {
        okHttpBuilder.addInterceptor(headerInterceptor)
        okHttpBuilder.addInterceptor(logger)
        okHttpBuilder.connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
        val client = okHttpBuilder.build()
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }



    //API link - https://exchangeratesapi.io/10:42
    //API link - https://exchangeratesapi.io/
}
