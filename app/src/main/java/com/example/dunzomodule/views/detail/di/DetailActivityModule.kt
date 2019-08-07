package com.example.dunzomodule.views.detail.di

import androidx.lifecycle.ViewModelProvider
import com.example.dunzomodule.datamanager.DataManager
import com.example.dunzomodule.utils.ViewModelProviderFactory
import com.example.dunzomodule.views.detail.viewmodel.DetailActivityViewModel
import dagger.Module
import dagger.Provides

@Module
class DetailActivityModule {

    @Provides
    fun providesDetailActivityViewModel(dataManager: DataManager): DetailActivityViewModel {
        return DetailActivityViewModel(dataManager)
    }

    @Provides
    fun providesViewModelProvider(detailActivityViewModel: DetailActivityViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(detailActivityViewModel)
    }
}