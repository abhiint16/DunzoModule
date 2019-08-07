package com.example.dunzomodule.di

import android.app.Application
import com.example.dunzomodule.DunzoApp
import com.example.dunzomodule.di.builder.ViewBuilderProvider
import com.example.dunzomodule.di.modules.AppModule
import com.example.dunzomodule.di.modules.ContextModule
import com.example.dunzomodule.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, ContextModule::class, NetworkModule::class,
        AndroidInjectionModule::class, ViewBuilderProvider::class]
)
interface AppComponent {

    fun inject(dunzoApp: DunzoApp)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}