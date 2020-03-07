package com.rax.googlenews.news.model.service

import com.rax.googlenews.news.model.service.impl.Dto
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {
    /**
     * Retrieves all the latest news article from Google news using News API.
     */
    @GET("articles?source=google-news&page={pageNo}&apiKey=")
    suspend fun getNewsFromGoogle(page: Int): Response<Dto>
}