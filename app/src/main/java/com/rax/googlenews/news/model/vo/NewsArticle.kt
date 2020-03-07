package com.rax.googlenews.news.model.vo

data class NewsArticle(

    val id: Int = 0,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null
)