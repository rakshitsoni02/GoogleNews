package com.rax.googlenews.news.model.service

import com.rax.googlenews.news.model.service.impl.JokeDto
import retrofit2.http.GET

/**
 * Created by Rax on 02/04/20.
 */

interface JokeService {

    @GET("jokes/random")
    suspend fun getRandomJoke(): JokeDto.JokeResult
}