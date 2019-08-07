package com.example.dunzomodule.datamanager.prefhelper

import android.content.Context
import android.content.SharedPreferences
import com.example.dunzomodule.di.qualifier.PreferenceName
import javax.inject.Inject

class PreferenceHelperImpl : PreferenceHelper {

    var context: Context
    var prefName: String
    var sharedPreferences: SharedPreferences

    private val SEARCH_STRING = "searchString"

    @Inject
    constructor(context: Context, @PreferenceName prefName: String) {
        this.context = context
        this.prefName = prefName
        this.sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    override fun saveSearchString(searchString: String) {
        sharedPreferences.edit().putString(SEARCH_STRING, searchString).apply()
    }

    override fun removeSharedPreference() {
        sharedPreferences.edit().clear().apply()
    }

    override fun getSearchString(): String {
        return sharedPreferences.getString(SEARCH_STRING, "searchString")!!
    }
}