package com.example.up.lt_mvvm_.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface  ExchangeRateDAO {

//    @Query("SELECT * FROM ExchangeRate WHERE base = :baseCurrency ")
//    fun getAll(baseCurrency: String): MutableList<ExchangeRate>
//
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCurrency(exchangeRate: ExchangeRate)
}