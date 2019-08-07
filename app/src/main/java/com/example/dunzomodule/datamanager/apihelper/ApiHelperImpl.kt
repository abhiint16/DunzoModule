package com.example.dunzomodule.datamanager.apihelper

import com.example.dunzomodule.di.qualifier.ApiKey
import com.example.dunzomodule.di.qualifier.CX
import com.example.dunzomodule.views.home.model.SearchBaseDataModel
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl : ApiHelper {

    var apiService: ApiService
    var cx: String
    var key: String

    @Inject
    constructor(apiService: ApiService, @CX cx: String, @ApiKey key: String) {
        this.apiService = apiService
        this.cx = cx
        this.key = key
    }

    override fun getSearchData(searchString: String, startNumber: Int): Single<Response<SearchBaseDataModel>> {
        return apiService.getSearchData(searchString, cx, key, startNumber)
    }
}