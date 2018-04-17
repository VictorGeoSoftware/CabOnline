package com.victor.test.cabonline.data.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by victorpalmacarrasco on 17/4/18.
 * ${APP_NAME}
 */
open class RealmBlogDto(
        @PrimaryKey var id: String? = null,
        var kind: String? = null,
        var published: String? = null,
        var updated: String? = null,
        var etag: String? = null,
        var url: String? = null,
        var selfLink: String? = null,
        var title: String? = null,
        var content: String? = null,
        var author: RealmAuthorDto? = null
): RealmObject()