package com.rax.googlenews.news.model.service.impl

import com.google.gson.annotations.SerializedName
import com.rax.googlenews.news.model.vo.NewsArticle

/**
 * Created by Rax on 07/03/20.
 */

data class Dto(
    @SerializedName("status")
    val status: String = "",

    @SerializedName("source")
    val source: String = "",

    @SerializedName("sortBy")
    val sortBy: String = "",

    @SerializedName("articles")
    val articles: List<NewsArticle> = emptyList()
)