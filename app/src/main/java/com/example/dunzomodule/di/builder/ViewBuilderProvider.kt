package com.example.dunzomodule.di.builder

import com.example.dunzomodule.views.home.HomeActivity
import com.example.dunzomodule.views.home.di.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewBuilderProvider {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun homeActivity(): HomeActivity
}