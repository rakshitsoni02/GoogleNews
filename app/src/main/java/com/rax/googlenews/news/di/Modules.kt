package com.rax.googlenews.news.di

import androidx.lifecycle.ViewModel
import com.rax.googlenews.core.di.base.ViewModelKey
import com.rax.googlenews.news.model.service.NewsService
import com.rax.googlenews.news.repo.NewsRepository
import com.rax.googlenews.news.repo.impl.NewsRepositoryImpl
import com.rax.googlenews.news.viewmodel.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
interface NewsRepositoryModule {
    @Binds
    fun bindNewsRepository(it: NewsRepositoryImpl): NewsRepository
}

@Module
interface NewsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    fun bindNewsArticleViewModel(newsArticleViewModel: NewsViewModel): ViewModel
}

@Module
object NewsServiceModule {

    private const val BASE_URL = "https://newsapi.org/v2/"

    @Singleton
    @Provides
    fun provideNewsService(): NewsService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
    }
}