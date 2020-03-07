package com.rax.googlenews.news.di

import androidx.lifecycle.ViewModel
import com.rax.googlenews.core.di.base.ViewModelKey
import com.rax.googlenews.news.viewmodel.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface NewsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    fun bindNewsArticleViewModel(newsArticleViewModel: NewsViewModel): ViewModel
}