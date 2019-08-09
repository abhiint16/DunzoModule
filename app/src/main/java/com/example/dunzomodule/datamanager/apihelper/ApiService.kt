package com.example.dunzomodule.datamanager.apihelper

import com.example.dunzomodule.views.home.model.SearchBaseDataModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    fun getSearchData(
        @Query("q") searchString: String,
        @Query("cx") cx: String,
        @Query("key") key: String,
        @Query("start") startNumber: Int
    ): Single<Response<SearchBaseDataModel>>


}