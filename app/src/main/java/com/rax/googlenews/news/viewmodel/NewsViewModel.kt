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

    init {
        loadMore(page = 1)
    }
    private val newsArticles2 = Transformations.switchMap(pageNo) { newPageNo ->
        newsRepository.getNewsArticles(newPageNo).asLiveData()
    }

    /**
     * Return news articles to observeNotNull on the UI.
     */
    fun getNewsArticles(): LiveData<ViewState<List<NewsArticle>>> = newsArticles2

    fun loadMore(page: Long) {
        pageNo.value = page
    }

    fun checkLastPage(): Boolean = newsRepository.isLastPage()

}
