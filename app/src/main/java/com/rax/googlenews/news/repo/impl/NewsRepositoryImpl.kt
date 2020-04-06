package com.rax.googlenews.news.repo.impl

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.rax.googlenews.core.view.ViewState
import com.rax.googlenews.news.model.service.JokeService
import com.rax.googlenews.news.model.service.NewsService
import com.rax.googlenews.news.model.service.impl.JokeDto
import com.rax.googlenews.news.model.vo.NewsArticle
import com.rax.googlenews.news.repo.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService,
    private val jokeService: JokeService
) :
    NewsRepository {
    private var isLastReached = false
    private val resultList: MutableList<NewsArticle> = mutableListOf()

    override fun getNewsArticles(page: Long): Flow<ViewState<MutableList<NewsArticle>>> {
        return flow {
            emit(ViewState.loading())
            val newsSource = newsService.getNewsFromGoogle(page = page)
            isLastReached = newsSource.articles.size < 21
            resultList.addAll(newsSource.articles)
            emit(ViewState.success(resultList))
        }.catch {
            emit(ViewState.error(it.message.orEmpty()))
        }.flowOn(Dispatchers.IO)
    }


    override fun isLastPage(): Boolean {
        return isLastReached
    }

    override fun getRandomJoke(): Flow<ViewState<JokeDto.JokeResult>> {
        return flow {
            emit(ViewState.loading())
            val result = jokeService.getRandomJoke()
            emit(ViewState.success(result))
        }.catch { throwable ->
            emit(ViewState.error(throwable.message.orEmpty()))
        }.flowOn(Dispatchers.IO)
    }
}