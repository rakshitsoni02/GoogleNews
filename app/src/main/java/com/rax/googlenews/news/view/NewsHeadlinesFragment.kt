package com.rax.googlenews.news.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rax.googlenews.R
import com.rax.googlenews.core.utils.getViewModel
import com.rax.googlenews.core.utils.observeNotNull
import com.rax.googlenews.core.utils.toast
import com.rax.googlenews.core.view.ViewState
import com.rax.googlenews.news.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.empty_layout.*
import kotlinx.android.synthetic.main.fragment_news_headlines.*
import kotlinx.android.synthetic.main.progress_layout.*


class NewsHeadlinesFragment : Fragment() {
    private val newsArticleViewModel by lazy { getViewModel<NewsViewModel>() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentOrientation = resources.configuration.orientation
        SPAN_COUNT = if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            3
        } else {
            2
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_news_headlines, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvNewsContainer.setEmptyView(empty_view)
        rvNewsContainer.setSpanConfiguration()
        rvNewsContainer.setPagingListener({ page ->
            newsArticleViewModel.loadMore(page = page)
        }, { newsArticleViewModel.checkLastPage() })
        rvNewsContainer.setProgressView(progress_view)
        val adapter = NewsArticlesAdapter { newsArticle ->
            newsArticle.url?.apply {
                newsArticleViewModel.updateNewsUrl(newsUrl = this)
//                (activity as NewsActivity).startDetails()
            }
        }
        rvNewsContainer.adapter = adapter

        newsArticleViewModel.getNewsArticles().observeNotNull(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Success -> adapter.submitList(state.data.toList())
                is ViewState.Loading -> rvNewsContainer.showLoading()
                is ViewState.Error -> activity?.toast("Something went wrong ¯\\_(ツ)_/¯ => ${state.message}")
            }
        }
    }

    companion object {
        fun newInstance() = NewsHeadlinesFragment()
        var SPAN_COUNT = 2
    }
}
