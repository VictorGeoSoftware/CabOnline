package com.victor.test.cabonline.data.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by victorpalmacarrasco on 17/4/18.
 * ${APP_NAME}
 */
open class RealmAuthorDto (
        @PrimaryKey var id: String? = null,
        var displayName: String? = null,
        var url: String? = null,
        var image: RealmImageDto? = null
): RealmObject()