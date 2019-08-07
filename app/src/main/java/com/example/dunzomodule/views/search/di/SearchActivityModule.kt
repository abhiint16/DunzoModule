package com.example.dunzomodule.views.search.di

import androidx.lifecycle.ViewModelProvider
import com.example.dunzomodule.datamanager.DataManager
import com.example.dunzomodule.utils.ViewModelProviderFactory
import com.example.dunzomodule.views.search.viewmodel.SearchActivityViewModel
import dagger.Module
import dagger.Provides

@Module
class SearchActivityModule {

    @Provides
    fun providesSearchActivityViewModel(dataManager: DataManager): SearchActivityViewModel {
        return SearchActivityViewModel(dataManager)
    }

    @Provides
    fun providesViewModelProvider(searchActivityViewModel: SearchActivityViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(searchActivityViewModel)
    }
}