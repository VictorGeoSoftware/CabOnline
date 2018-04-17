package com.victor.test.cabonline.data.models

import io.realm.RealmObject

/**
 * Created by victorpalmacarrasco on 15/4/18.
 * ${APP_NAME}
 */
data class AuthorDto(
        val id: String,
        val displayName: String,
        val url: String,
        val image: ImageDto
)