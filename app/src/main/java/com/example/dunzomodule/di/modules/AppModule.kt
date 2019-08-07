package com.example.dunzomodule.di.modules

import com.example.dunzomodule.AppConstants
import com.example.dunzomodule.BuildConfig
import com.example.dunzomodule.datamanager.DataManager
import com.example.dunzomodule.datamanager.DataManagerImpl
import com.example.dunzomodule.datamanager.apihelper.ApiHelper
import com.example.dunzomodule.datamanager.apihelper.ApiHelperImpl
import com.example.dunzomodule.datamanager.apihelper.ApiService
import com.example.dunzomodule.datamanager.dbhelper.DBHelper
import com.example.dunzomodule.datamanager.dbhelper.DBHelperImpl
import com.example.dunzomodule.datamanager.prefhelper.PreferenceHelper
import com.example.dunzomodule.datamanager.prefhelper.PreferenceHelperImpl
import com.example.dunzomodule.di.qualifier.ApiKey
import com.example.dunzomodule.di.qualifier.CX
import com.example.dunzomodule.di.qualifier.PreferenceName
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun providesDataManager(dataManagerImpl: DataManagerImpl):
            DataManager {
        return dataManagerImpl
    }

    @Provides
    @Singleton
    fun providesApiHelper(apiHelper: ApiHelperImpl):
            ApiHelper {
        return apiHelper
    }

    @Provides
    fun providesDBHelper(dbHelper: DBHelperImpl):
            DBHelper {
        return dbHelper
    }

    @Provides
    fun providesPrefHelper(preferenceHelper: PreferenceHelperImpl):
            PreferenceHelper {
        return preferenceHelper
    }

    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @CX
    fun providesCX(): String {
        return BuildConfig.CX
    }

    @Provides
    @ApiKey
    fun providesApiKey(): String {
        return BuildConfig.API_KEY
    }

    @Provides
    @PreferenceName
    fun providesSharedPrefName(): String {
        return AppConstants.SharedPref.SHARED_PREFERENCE_NAME;
    }
}