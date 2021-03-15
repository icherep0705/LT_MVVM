package com.example.up.lt_mvvm_.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface  ExchangeRateDAO {

    @Query("SELECT * FROM ExchangeRate WHERE base = :baseCurrency ")
    fun getAll(baseCurrency: String): List<ExchangeRate>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCurrency(exchangeRate: ExchangeRate)

    @Query("DELETE FROM ExchangeRate WHERE base = :baseCurrency ")
    fun deleteAll(baseCurrency: String)

}