package com.rax.googlenews.news.di

import androidx.lifecycle.ViewModel
import com.rax.googlenews.core.di.base.ViewModelKey
import com.rax.googlenews.news.model.service.JokeService
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
    @Singleton
    @ViewModelKey(NewsViewModel::class)
    fun bindNewsArticleViewModel(newsArticleViewModel: NewsViewModel): ViewModel
}

@Module
object NewsServiceModule {

    private const val BASE_URL = "https://newsapi.org/v2/"
    private const val JOKE_SERVICE_BASE_URL = "https://api.chucknorris.io/"

    @Singleton
    @Provides
    fun provideNewsService(): NewsService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
    }

    @Singleton
    @Provides
    fun provideJokeService(): JokeService {
        return Retrofit.Builder()
            .baseUrl(JOKE_SERVICE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JokeService::class.java)
    }
}