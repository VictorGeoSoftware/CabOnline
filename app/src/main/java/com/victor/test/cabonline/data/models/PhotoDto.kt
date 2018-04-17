package com.victor.test.cabonline.data.models

/**
 * Created by victorpalmacarrasco on 15/4/18.
 * ${APP_NAME}
 */
data class PhotoDto(val format: String,
                    val width: Long,
                    val height: Long,
                    val filename: String,
                    val id: Long,
                    val author: String,
                    val author_url: String,
                    val post_url: String
)