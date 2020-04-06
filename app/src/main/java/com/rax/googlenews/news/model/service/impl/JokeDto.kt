package com.rax.googlenews.news.model.service.impl

/**
 * Created by Rax on 02/04/20.
 */

class JokeDto {

    data class JokeResult(
        val created_at: String,
        val icon_url: String,
        val id: String,
        val updated_at: String,
        val url: String,
        val value: String
    )

}