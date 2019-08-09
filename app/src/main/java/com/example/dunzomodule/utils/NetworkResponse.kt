package com.example.dunzomodule.utils

import io.reactivex.MaybeObserver
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import retrofit2.Response

abstract class NetworkResponse<ServerResponse, DesiredResponse> : MaybeObserver<ServerResponse> {

    abstract fun onApiSuccess(desiredResponse: DesiredResponse)

    abstract fun onApiError(throwable: Throwable)

    abstract fun addDisposable(d: Disposable)

    override fun onSuccess(serverResponse: ServerResponse) {
        val response = serverResponse as Response<*>

        if (response.isSuccessful) {

            if (response.body() == null) {
                onApiError(Throwable("Something went wrong..."))
                return
            }

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