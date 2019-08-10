package com.example.dunzomodule.di.modules

import android.content.Context
import android.util.Log
import com.example.dunzomodule.BuildConfig
import com.example.dunzomodule.di.qualifier.BaseUrl
import com.example.dunzomodule.utils.network.NetworkInterceptor
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @BaseUrl
    fun providesBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    fun providesOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ChuckInterceptor(context))
            .addInterceptor(NetworkInterceptor(context))
            .addInterceptor(Interceptor { chain ->
                val requestBody = chain.request()
                val responseBody = chain.proceed(requestBody)
                return@Interceptor responseBody
            })
            .build()
    }

    @Provides
    fun providesRetrofit(@BaseUrl baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }
}