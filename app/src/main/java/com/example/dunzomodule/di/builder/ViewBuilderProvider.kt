package com.example.dunzomodule.di.builder

import com.example.dunzomodule.views.HomeActivity
import com.example.dunzomodule.views.di.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewBuilderProvider {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun homeActivity(): HomeActivity
}