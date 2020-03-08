package com.rax.googlenews.news.viewmodel

import androidx.lifecycle.*
import com.rax.googlenews.core.view.ViewState
import com.rax.googlenews.news.model.vo.NewsArticle
import com.rax.googlenews.news.repo.NewsRepository
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val pageNo: MutableLiveData<Long> = MutableLiveData()
    private val newsUrlForDetailsPage = MutableLiveData<String>()

    init {
        loadMore(page = 1)
    }
    private val newsArticlesList = Transformations.switchMap(pageNo) { newPageNo ->
        newsRepository.getNewsArticles(newPageNo).asLiveData()
    }

    /**
     * Return news articles to observeNotNull on the UI.
     */
    fun getNewsArticles(): LiveData<ViewState<List<NewsArticle>>> = newsArticlesList

    fun loadMore(page: Long) {
        pageNo.value = page
    }

    fun openDetailsPage(): MutableLiveData<String> = newsUrlForDetailsPage

    fun checkLastPage(): Boolean = newsRepository.isLastPage()

    fun updateNewsUrl(newsUrl: String) {
        this.newsUrlForDetailsPage.value = newsUrl
    }

    fun getNewsUrl(): String? = newsUrlForDetailsPage.value
}
