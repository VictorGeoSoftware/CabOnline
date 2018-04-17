package com.victor.test.cabonline.data.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by victorpalmacarrasco on 17/4/18.
 * ${APP_NAME}
 */
open class RealmStoreDto(
        @PrimaryKey var id: Long? = 0,
        var code: String? = null,
        var label: String? = null,
        var street: String? = null,
        var city: String? = null,
        var zipCode: String? = null,
        var province: String? = null,
        var country: String? = null,
        var latitude: Double? = null,
        var longitude: Double? = null,
        var phoneNumber: String? = null,
        var email: String? = null
): RealmObject()