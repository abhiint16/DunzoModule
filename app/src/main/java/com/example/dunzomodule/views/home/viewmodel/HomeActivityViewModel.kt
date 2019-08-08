package com.example.dunzomodule.views.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dunzomodule.datamanager.DataManager
import com.example.dunzomodule.views.home.model.SearchBaseDataModel
import com.example.dunzomodule.views.home.model.items.ItemsInnerObjectDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeActivityViewModel : ViewModel {
    var dataManager: DataManager

    internal var mutableBaseLiveData = MutableLiveData<SearchBaseDataModel>()

    internal var mutableErrorLiveData = MutableLiveData<String>()

    internal var itemClickLiveData = MutableLiveData<ItemsInnerObjectDataModel>()

    lateinit var disposable: Disposable

    var startNumber = 0

    constructor(dataManager: DataManager) : super() {
        this.dataManager = dataManager
    }

    fun getSearchData() {
        checkForStartNumber()

        disposable = dataManager.getSearchData(dataManager.getSearchString(), startNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mutableBaseLiveData.value = it.body()
            }, {
                mutableErrorLiveData.value = it.message
            })
    }

    fun homeItemClick(itemsInnerObjectDataModel: ItemsInnerObjectDataModel) {
        itemClickLiveData.value = itemsInnerObjectDataModel
    }

    private fun checkForStartNumber() {
        if (startNumber == 0)
            startNumber = 1
        else
            startNumber += 10
    }

    fun observeForBaseLiveData(): LiveData<SearchBaseDataModel> {
        return mutableBaseLiveData
    }

    fun observeForErrorLiveData(): LiveData<String> {
        return mutableErrorLiveData
    }

    fun observeForItemClickLiveData(): LiveData<ItemsInnerObjectDataModel> {
        return itemClickLiveData
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}