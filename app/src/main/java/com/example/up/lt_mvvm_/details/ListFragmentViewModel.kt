package com.example.up.lt_mvvm_.details

import android.util.Log
import androidx.lifecycle.ViewModel

class ListFragmentViewModel(private val currency: String): ViewModel() {

    init {
        Log.d(TAG, "ViewModel: $currency")
    }

    fun doSomething() {
        Log.d(TAG, "do something")
    }

    companion object {
        private val TAG = ListFragmentViewModel::class.java.simpleName
    }
}