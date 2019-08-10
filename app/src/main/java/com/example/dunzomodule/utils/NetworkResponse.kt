package com.example.dunzomodule.utils

import android.util.Log
import com.google.gson.Gson
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import retrofit2.Response

abstract class NetworkResponse<ServerResponse, DesiredResponse> : SingleObserver<ServerResponse> {

    abstract fun onApiSuccess(desiredResponse: DesiredResponse)

    abstract fun onApiError(throwable: Throwable)

    abstract fun addDisposable(d: Disposable)

    override fun onSuccess(serverResponse: ServerResponse) {
        val response = serverResponse as Response<*>

        Log.e("check4 desired response", "" + Gson().toJson(response.body()))
        if (response.isSuccessful) {

            Log.e("check4 desired response", "" + Gson().toJson(response.body()))

            if (response.body() == null) {
                onApiError(Throwable("Something went wrong..."))
                return
            }
            //Log.e("check4 desired response", "" + response.body())
            onApiSuccess(response.body() as DesiredResponse)
        } else {
            onApiError(Throwable(response.body().toString()))
        }
    }

    override fun onSubscribe(d: Disposable) {
        addDisposable(d)
    }

    override fun onError(throwable: Throwable) {
        onApiError(throwable)
    }
}