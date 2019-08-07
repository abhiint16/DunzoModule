package com.example.dunzomodule.datamanager

import com.example.dunzomodule.datamanager.apihelper.ApiHelper
import com.example.dunzomodule.datamanager.dbhelper.DBHelper
import com.example.dunzomodule.datamanager.prefhelper.PreferenceHelper
import javax.inject.Inject

class DataManagerImpl : DataManager {

    var apiHelper: ApiHelper
    var dbHelper: DBHelper
    var preferenceHelper: PreferenceHelper

    @Inject
    constructor(apiHelper: ApiHelper, dbHelper: DBHelper, preferenceHelper: PreferenceHelper) {
        this.apiHelper = apiHelper
        this.dbHelper = dbHelper
        this.preferenceHelper = preferenceHelper
    }


    ///////////////////    Preferance Starts   //////////////////////////


    override fun saveSearchString(searchString: String) {
        preferenceHelper.saveSearchString(searchString)
    }

    override fun removeSharedPreference() {
        preferenceHelper.removeSharedPreference()
    }

    override fun getSearchString(): String {
        return preferenceHelper.getSearchString()
    }


    ///////////////////////    Prefernace ends    ///////////////////////////


    //////////////////////    API starts      //////////////////////////////

}