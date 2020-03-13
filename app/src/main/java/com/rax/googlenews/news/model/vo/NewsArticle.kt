package com.rax.googlenews.news.model.vo

data class NewsArticle(

    val source: Source? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null
)

data class Source(
    val id: String? = null,
    val name: String
)