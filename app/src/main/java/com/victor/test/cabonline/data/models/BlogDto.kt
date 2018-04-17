package com.victor.test.cabonline.data.models

/**
 * Created by victorpalmacarrasco on 15/4/18.
 * ${APP_NAME}
 */
data class BlogDto(
        val kind: String,
        val id: String,
        val published: String,
        val updated: String,
        val etag: String,
        val url: String,
        val selfLink: String,
        val title: String,
        val content: String,
        val author: AuthorDto
)