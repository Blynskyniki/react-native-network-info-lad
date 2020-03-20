package com.reactlibrary.NetWorkInfoLadLib

import android.net.DhcpInfo
import android.net.NetworkInfo
import com.facebook.react.bridge.*
import org.jetbrains.anko.connectivityManager
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.wifiManager
import java.util.*
import kotlin.concurrent.schedule

class RNNetworkInfoModuleLad : ReactContextBaseJavaModule {
    inner class NetworkData {
        lateinit var type: String
        var isConnected: Boolean = false
        lateinit var gateway: String
        lateinit var dns1: String
        lateinit var dns2: String
        lateinit var ipAddress: String
        lateinit var netmask: String
        lateinit var serverAddress: String

        fun checkGateway(gateway:String): Boolean {
            return isConnected && this.gateway == gateway;
        }
        fun loadParams(networkInfo:NetworkInfo?,info:DhcpInfo?){
            if (networkInfo !== null){
                this.type = networkInfo.subtypeName;
                this.isConnected = networkInfo.isConnected
            }
            if (info !== null){
                this.gateway = NetworkUtilsLad.intToIp(info.gateway)
                this.dns1 =  info.dns1.toString()
                this.dns2 =  info.dns2.toString()
                this.ipAddress = NetworkUtilsLad.intToIp(info.ipAddress)
                this.netmask  = info.netmask.toString()
                this.serverAddress = info.serverAddress.toString()
            }
        }
        fun toWritableMap(): WritableMap {
            val params = Arguments.createMap()
            params.putString("type", type)
            params.putBoolean("isConnected", isConnected)
            params.putString("gateway", gateway)
            params.putString("dns1", dns1)
            params.putString("dns2", dns2)
            params.putString("ipAddress", ipAddress)
            params.putString("netmask", netmask)
            params.putString("serverAddress", serverAddress)
            return params
        }
    }
    private val info: NetworkData = NetworkData()
    private val reactContext: ReactApplicationContext
    override fun getName(): String {
        return "RNNetworkInfoLad"
    }

    constructor(reactContext: ReactApplicationContext) : super(reactContext) {
        this.reactContext = reactContext;
        doAsync {
            Timer("NetworkChangesInfo", true)
                    .schedule(500) {
                        info.loadParams(reactContext.connectivityManager.activeNetworkInfo,reactContext.wifiManager.dhcpInfo)
                    }
        }
    }

    @ReactMethod
    fun checkGateWay(gateway:String,promise: Promise) {
        return promise.resolve(info.checkGateway(gateway))
    }

    @ReactMethod
    fun getNetworkInfo(promise: Promise) {
        try {
            promise.resolve(info.toWritableMap())
        } catch (e: Exception) {
            promise!!.resolve(mapError(e))
        }
    }

    private fun mapError(e: Exception): WritableMap {
        val params = Arguments.createMap()
        params.putString("errorCode", "1")
        params.putString("errorMsg", e.message)
        return params

    }
}