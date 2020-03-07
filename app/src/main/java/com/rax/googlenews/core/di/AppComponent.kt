package com.rax.googlenews.core.di

import android.app.Application
import com.rax.googlenews.App
import com.rax.googlenews.news.di.NewsFeatureBindingModule
import com.rax.googlenews.news.di.NewsServiceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        // Dagger support
        AndroidInjectionModule::class,

        // Global
        NewsServiceModule::class,
        ViewModelFactoryModule::class,

        // News feature
        NewsFeatureBindingModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    override fun inject(newsApp: App)
}
