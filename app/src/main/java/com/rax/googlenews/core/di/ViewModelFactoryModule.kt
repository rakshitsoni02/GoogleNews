package com.rax.googlenews.core.di

import androidx.lifecycle.ViewModelProvider
import com.rax.googlenews.core.di.base.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}