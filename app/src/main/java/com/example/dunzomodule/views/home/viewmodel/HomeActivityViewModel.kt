package com.example.dunzomodule.views.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dunzomodule.datamanager.DataManager
import com.example.dunzomodule.views.home.model.SearchBaseDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeActivityViewModel : ViewModel {
    var dataManager: DataManager

    internal var mutableBaseLiveData = MutableLiveData<SearchBaseDataModel>()

    internal var mutableErrorLiveData = MutableLiveData<String>()

    lateinit var disposable: Disposable

    constructor(dataManager: DataManager) : super() {
        this.dataManager = dataManager
    }

    fun getSearchData() {
        disposable = dataManager.getSearchData(dataManager.getSearchString(), 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mutableBaseLiveData.value = it.body()
            }, {
                mutableErrorLiveData.value = it.message
            })
    }

    fun observeForBaseLiveData(): LiveData<SearchBaseDataModel> {
        return mutableBaseLiveData
    }

    fun observeForErrorLiveData(): LiveData<String> {
        return mutableErrorLiveData
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}