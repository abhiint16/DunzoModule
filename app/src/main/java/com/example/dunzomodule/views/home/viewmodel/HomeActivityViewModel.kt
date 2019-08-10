package com.example.dunzomodule.views.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dunzomodule.datamanager.DataManager
import com.example.dunzomodule.utils.NetworkResponse
import com.example.dunzomodule.views.baseview.BaseViewModel
import com.example.dunzomodule.views.home.model.SearchBaseDataModel
import com.example.dunzomodule.views.home.model.items.ItemsInnerObjectDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class HomeActivityViewModel : BaseViewModel {
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
        showLoading()

        dataManager.getSearchData(dataManager.getSearchString(), startNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : NetworkResponse<Response<SearchBaseDataModel>, SearchBaseDataModel>() {

                override fun addDisposable(d: Disposable) {
                    disposable = d
                }

                override fun onApiSuccess(desiredResponse: SearchBaseDataModel) {
                    mutableBaseLiveData.value = desiredResponse
                    hideLoading()
                }

                override fun onApiError(throwable: Throwable) {
                    mutableErrorLiveData.value = throwable.message
                    hideLoading()
                }
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