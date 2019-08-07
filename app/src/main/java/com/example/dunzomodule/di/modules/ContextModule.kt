package com.example.dunzomodule.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class ContextModule {

    @Provides
    fun providesApplicationContext(application: Application): Context {
        return application
    }
}