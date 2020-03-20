package com.reactlibrary.NetWorkInfoLadLib

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.DhcpInfo
import android.net.wifi.WifiManager

object NetworkUtilsLad {
    fun getNetworkInfo(cntx:Context): DhcpInfo? {
        val data = cntx.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return data.dhcpInfo;

    }

    fun intToIp(i: Int): String {

        return (i and 0xFF).toString() + "." +
                (i shr 8 and 0xFF) + "." +
                (i shr 16 and 0xFF) + "." +
                (i shr 24 and 0xFF)
    }


}