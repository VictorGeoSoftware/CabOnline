package com.victor.test.cabonline.data

import com.victor.test.cabonline.data.models.StoreListByCityDto
import io.realm.RealmObject

/**
 * Created by victorpalmacarrasco on 15/4/18.
 * ${APP_NAME}
 */

// TODO :: alguno de los objetos esta mal
data class StoreListByCity(
        val stores: HashMap<String, StoreListByCityDto>,
        val errors: ArrayList<WsError>,
        val executeTime:Long
)