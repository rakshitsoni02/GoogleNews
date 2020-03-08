package com.rax.googlenews.news.model.service.impl

import com.google.gson.annotations.SerializedName
import com.rax.googlenews.news.model.vo.NewsArticle

data class Dto(
    @SerializedName("status")
    val status: String = "",

    @SerializedName("totalResults")
    val totalResults: Int,

    @SerializedName("articles")
    val articles: List<NewsArticle> = emptyList()


)