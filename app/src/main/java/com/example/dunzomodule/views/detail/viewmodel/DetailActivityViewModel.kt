package com.example.dunzomodule.views.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dunzomodule.datamanager.DataManager
import com.example.dunzomodule.views.baseview.BaseViewModel

class DetailActivityViewModel(dataManager: DataManager) : BaseViewModel(dataManager) {

    internal var mutableLiveData = MutableLiveData<Boolean>()

    fun testFun() {
        mutableLiveData.value = true
    }

    fun observeForLiveData(): LiveData<Boolean> {
        return mutableLiveData
    }
}