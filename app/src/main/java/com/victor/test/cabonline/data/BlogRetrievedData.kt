package com.victor.test.cabonline.data

import com.victor.test.cabonline.data.models.BlogDto
import io.realm.RealmObject

/**
 * Created by victorpalmacarrasco on 15/4/18.
 * ${APP_NAME}
 */
data class BlogRetrievedData(
        val kind: String,
        val items: ArrayList<BlogDto>,
        val etag: String
//): RealmObject()
)