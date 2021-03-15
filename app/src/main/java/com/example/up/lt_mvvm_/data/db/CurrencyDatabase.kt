package com.example.up.lt_mvvm_.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ExchangeRate::class], version = 1, exportSchema = false)
abstract class CurrencyDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "currency-db"
        private var instance: CurrencyDatabase? = null

        fun getInstance(context: Context): CurrencyDatabase? {
            if (instance == null) {
                synchronized(CurrencyDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        CurrencyDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }

    abstract fun exchangeRateDao(): ExchangeRateDAO
}