package com.rax.googlenews.news.di

import com.rax.googlenews.news.view.NewsActivity
import com.rax.googlenews.news.view.NewsHeadlinesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Describes list of activities which require
 * DI.
 *
 * Each [ContributesAndroidInjector] generates a sub-component
 * for each activity under the hood
 */
@Module(
    includes = [
        NewsViewModelModule::class,
        NewsRepositoryModule::class,
        NewsServiceModule::class
    ]
)
interface NewsFeatureBindingModule {

    @ContributesAndroidInjector
    fun contributeNewsActivity(): NewsActivity
}

