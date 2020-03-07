package com.rax.googlenews.news.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rax.googlenews.R

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NewsHeadlinesFragment.newInstance())
                .commitNow()
        }
    }

}
