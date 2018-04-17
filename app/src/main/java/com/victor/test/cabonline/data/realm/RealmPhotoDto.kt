package com.victor.test.cabonline.data.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by victorpalmacarrasco on 17/4/18.
 * ${APP_NAME}
 */
open class RealmPhotoDto (
        @PrimaryKey var id: Long? = null,
        var format: String? = null,
        var width: Long? = null,
        var height: Long? = null,
        var filename: String? = null,
        var author: String? = null,
        var author_url: String? = null,
        var post_url: String? = null
): RealmObject()