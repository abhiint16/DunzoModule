package com.example.dunzomodule.views.home.di

import androidx.lifecycle.ViewModelProvider
import com.example.dunzomodule.datamanager.DataManager
import com.example.dunzomodule.utils.ViewModelProviderFactory
import com.example.dunzomodule.views.home.viewmodel.HomeActivityViewModel
import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {

    @Provides
    fun providesHomeActivityViewModel(dataManager: DataManager): HomeActivityViewModel {
        return HomeActivityViewModel(dataManager)
    }

    @Provides
    fun providesViewModelProvider(homeActivityViewModel: HomeActivityViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(homeActivityViewModel)
    }
}