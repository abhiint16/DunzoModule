package com.example.dunzomodule.utils.network

import android.os.Handler
import android.os.Looper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject

object NetworkEvent {

    private var subject: PublishSubject<NetworkState>? = null

    private val compositeDisposableMap = HashMap<Any, CompositeDisposable>()

    private fun getSubject(): PublishSubject<NetworkState> {
        if (subject == null) {
            subject = PublishSubject.create()
            subject!!.subscribeOn(AndroidSchedulers.mainThread())
        }
        return subject as PublishSubject<NetworkState>
    }

    private fun getCompositeSubscription(anyObject: Any): CompositeDisposable {
        var compositeSubscription: CompositeDisposable? = compositeDisposableMap[anyObject]
        if (compositeSubscription == null) {
            compositeSubscription = CompositeDisposable()
            compositeDisposableMap[anyObject] = compositeSubscription
        }
        return compositeSubscription
    }

    //this method is to Publish the NetworkState to all the specified subscribers of the subject.
    fun publish(networkState: NetworkState) {
        Handler(Looper.getMainLooper()).post {
            getSubject().onNext(networkState)
        }
    }

    fun register(lifecycle: Any, action: Consumer<NetworkState>) {
        val disposable = getSubject().subscribe(action)
        getCompositeSubscription(lifecycle).add(disposable)
    }

    fun unregister(lifecycle: Any) {
        val compositeSubscription = compositeDisposableMap.remove(lifecycle)
        compositeSubscription?.dispose()
    }
}