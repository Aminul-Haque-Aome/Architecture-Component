package com.remotearth.fake_coder.architecturecomponent.wrappers

import android.content.Context
import android.net.ConnectivityManager

class NetManager(private var applicationContext: Context)  {

    private var status: Boolean? = false

    val isConnectedToInternet: Boolean?
        get() {
            val manager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return manager.activeNetworkInfo != null && manager.activeNetworkInfo.isConnected
        }
}