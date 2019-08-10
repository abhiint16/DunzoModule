package com.example.dunzomodule.utils.network

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkInterceptor(private val context: Context) : Interceptor {
    private val networkEvent: NetworkEvent = NetworkEvent

    override fun intercept(chain: Interceptor.Chain): Response? {
        val request = chain.request()

        if (!ConnectivityStatus.isConnected(context)) {
            networkEvent.publish(NetworkState.NO_INTERNET)

        } else {
            try {
                val response = chain.proceed(request)
                when (response.code()) {
                    200 -> networkEvent.publish(NetworkState.SUCCESS)

                    403 -> networkEvent.publish(NetworkState.BILLINGERROR)

                    401 -> networkEvent.publish(NetworkState.UNAUTHORISED)

                    503 -> networkEvent.publish(NetworkState.NO_RESPONSE)

                    412 -> networkEvent.publish(NetworkState.CX_NOT_DEFINED)
                }
                return response

            } catch (e: IOException) {
                networkEvent.publish(NetworkState.NO_RESPONSE)
            }
        }
        return null
    }
}