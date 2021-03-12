package com.example.up.lt_mvvm_.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListFragmentViewModelFactory(private val currency: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListFragmentViewModel::class.java)) {
            return ListFragmentViewModel(currency) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}