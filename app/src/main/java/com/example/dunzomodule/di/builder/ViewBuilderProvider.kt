package com.example.dunzomodule.di.builder

import com.example.dunzomodule.views.detail.DetailActivity
import com.example.dunzomodule.views.detail.di.DetailActivityModule
import com.example.dunzomodule.views.home.HomeActivity
import com.example.dunzomodule.views.home.di.HomeActivityModule
import com.example.dunzomodule.views.search.SearchActivity
import com.example.dunzomodule.views.search.di.SearchActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewBuilderProvider {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [SearchActivityModule::class])
    abstract fun searchActivity(): SearchActivity

    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    abstract fun detailActivity(): DetailActivity
}