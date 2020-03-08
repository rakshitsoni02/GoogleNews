package com.rax.googlenews.news.repo.impl

import com.rax.googlenews.core.view.ViewState
import com.rax.googlenews.news.model.service.NewsService
import com.rax.googlenews.news.model.vo.NewsArticle
import com.rax.googlenews.news.repo.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(private val newsService: NewsService) :
    NewsRepository {
    private var isLastReached = false

    override fun getNewsArticles(page: Long): Flow<ViewState<List<NewsArticle>>> {
        return flow {
            emit(ViewState.loading())
            val newsSource = newsService.getNewsFromGoogle(page = page)
            isLastReached = newsSource.articles.size < 21
            emit(ViewState.success(newsSource.articles))
        }.catch {
            emit(ViewState.error(it.message.orEmpty()))
        }.flowOn(Dispatchers.IO)
    }

    override fun isLastPage(): Boolean {
        return isLastReached
    }
}