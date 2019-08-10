package com.example.dunzomodule.views.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dunzomodule.datamanager.DataManager
import com.example.dunzomodule.views.baseview.BaseViewModel

class SearchActivityViewModel(dataManager: DataManager) : BaseViewModel(dataManager) {

    var mutableLiveData = MutableLiveData<Boolean>()

    fun saveSearchStringToPref(searchString: String) {
        dataManager.saveSearchString(searchString)
        mutableLiveData.value = true
    }

    fun observeForLiveData(): LiveData<Boolean> {
        return mutableLiveData
    }
}