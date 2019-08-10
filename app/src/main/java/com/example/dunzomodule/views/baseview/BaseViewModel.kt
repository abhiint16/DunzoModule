package com.example.dunzomodule.views.baseview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private var loadingLiveData = MutableLiveData<Boolean>()

    protected fun showLoading() {
        loadingLiveData.value = true
    }

    protected fun hideLoading() {
        loadingLiveData.value = false
    }

    fun observeForDialog(): LiveData<Boolean> {
        return loadingLiveData
    }
}