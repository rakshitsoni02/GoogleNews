package com.rax.googlenews.news.model.service

import com.rax.googlenews.news.model.service.impl.Dto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    /**
     * Retrieves all the latest news article from Google news using News API.
     */
    @GET("top-headlines?country=de&pageSize=21&&apiKey=63ca4ca071294285994e39a57863f0a9")
    suspend fun getNewsFromGoogle(@Query("page") page: Long): Dto
}