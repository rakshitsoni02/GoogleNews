package com.rax.googlenews.news.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.rax.googlenews.R
import com.rax.googlenews.core.utils.getViewModel
import com.rax.googlenews.core.utils.observeNotNull
import com.rax.googlenews.core.view.ViewState
import com.rax.googlenews.news.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news_detail.*


class NewsDetailFragment : Fragment() {
    private val newsArticleViewModel by lazy { getViewModel<NewsViewModel>() }
    private var toast: Toast? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        webView.webViewClient = WebViewClient()
        webView.loadUrl(newsArticleViewModel.getNewsUrl())
        newsArticleViewModel.getRandomJoke().observeNotNull(viewLifecycleOwner) { state ->
            if (state is ViewState.Success) {
                toast = Toast.makeText(activity, state.data.value, Toast.LENGTH_LONG)
                toast?.show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        toast?.cancel()
    }
}