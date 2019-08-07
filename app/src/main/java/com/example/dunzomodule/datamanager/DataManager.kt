package com.example.dunzomodule.datamanager

import com.example.dunzomodule.datamanager.apihelper.ApiHelper
import com.example.dunzomodule.datamanager.dbhelper.DBHelper
import com.example.dunzomodule.datamanager.prefhelper.PreferenceHelper

interface DataManager : ApiHelper, DBHelper, PreferenceHelper {
}