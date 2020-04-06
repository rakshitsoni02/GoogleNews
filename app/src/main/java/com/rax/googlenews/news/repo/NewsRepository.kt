package com.rax.googlenews.news.repo

import com.rax.googlenews.core.view.ViewState
import com.rax.googlenews.news.model.service.impl.JokeDto
import com.rax.googlenews.news.model.vo.NewsArticle
import kotlinx.coroutines.flow.Flow

/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source as the single source of truth.
 */
interface NewsRepository {
    /**
     * Gets tne cached news article from database and tries to get
     * fresh news articles from web and save into database
     * if that fails then continues showing cached data.
     */
    fun getNewsArticles(page: Long): Flow<ViewState<MutableList<NewsArticle>>>

    fun isLastPage(): Boolean

    fun getRandomJoke(): Flow<ViewState<JokeDto.JokeResult>>

}