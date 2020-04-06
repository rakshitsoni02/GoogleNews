package com.rax.googlenews.news.viewmodel

import androidx.lifecycle.*
import com.rax.googlenews.core.utils.SingleLiveEvent
import com.rax.googlenews.core.view.ViewState
import com.rax.googlenews.news.model.vo.NewsArticle
import com.rax.googlenews.news.repo.NewsRepository
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val pageNo: MutableLiveData<Long> = MutableLiveData()
    private var newsUrlForDetailsPage = SingleLiveEvent<String>()
    private val randomJoke = newsRepository.getRandomJoke().asLiveData()

    init {
        loadMore(page = 1)
    }

    private val newsArticlesList = Transformations.switchMap(pageNo) { newPageNo ->
        newsRepository.getNewsArticles(newPageNo).asLiveData()
    }

    /**
     * Return news articles to observeNotNull on the UI.
     */
    fun getNewsArticles(): LiveData<ViewState<MutableList<NewsArticle>>> = newsArticlesList

    fun loadMore(page: Long) {
        pageNo.value = page
    }


    fun checkLastPage(): Boolean = newsRepository.isLastPage()

    fun updateNewsUrl(newsUrl: String) {
        newsUrlForDetailsPage.value = newsUrl
    }

    fun openDetailsPage(): MutableLiveData<String> = newsUrlForDetailsPage

    fun getNewsUrl(): String? = newsUrlForDetailsPage.value

    fun getRandomJoke() = randomJoke
}
