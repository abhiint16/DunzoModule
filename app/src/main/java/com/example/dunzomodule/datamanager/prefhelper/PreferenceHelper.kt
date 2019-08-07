package com.example.dunzomodule.datamanager.prefhelper

interface PreferenceHelper {

    fun saveSearchString(searchString: String)

    fun removeSharedPreference()

    fun getSearchString() : String
}