package com.rax.googlenews.news.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.rax.googlenews.R
import com.rax.googlenews.core.utils.getViewModel
import com.rax.googlenews.core.view.activity.BaseActivity
import com.rax.googlenews.news.viewmodel.NewsViewModel

class NewsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NewsHeadlinesFragment.newInstance())
                .commitNow()
        }
    }
}
