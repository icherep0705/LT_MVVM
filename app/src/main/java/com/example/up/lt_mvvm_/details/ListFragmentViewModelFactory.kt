package com.example.up.lt_mvvm_.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListFragmentViewModelFactory(
        private val currency: String,
        private val isConnected: Boolean,
        private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListFragmentViewModel::class.java)) {
            return ListFragmentViewModel(currency, isConnected, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}