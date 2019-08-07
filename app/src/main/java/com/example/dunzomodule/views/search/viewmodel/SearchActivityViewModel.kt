package com.example.dunzomodule.views.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dunzomodule.datamanager.DataManager

class SearchActivityViewModel : ViewModel {
    var dataManager: DataManager

    var mutableLiveData = MutableLiveData<Boolean>()

    constructor(dataManager: DataManager) : super() {
        this.dataManager = dataManager
    }

    fun saveSearchStringToPref(searchString: String) {
        dataManager.saveSearchString(searchString)
        mutableLiveData.value = true
    }

    fun observeForLiveData(): LiveData<Boolean> {
        return mutableLiveData
    }
}