package com.example.up.lt_mvvm_

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object Utils {

     fun isNetworkConnected(cxt: Context): Boolean {
        val cm = cxt.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}