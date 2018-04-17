package com.victor.test.cabonline.data.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by victorpalmacarrasco on 17/4/18.
 * ${APP_NAME}
 */
open class RealmImageDto (
        @PrimaryKey var url: String? = null
): RealmObject()