package com.example.dunzomodule.datamanager.apihelper

import com.example.dunzomodule.views.home.model.SearchBaseDataModel
import io.reactivex.Single
import retrofit2.Response

interface ApiHelper {

    fun getSearchData(searchString: String, startNumber: Int): Single<Response<SearchBaseDataModel>>
}