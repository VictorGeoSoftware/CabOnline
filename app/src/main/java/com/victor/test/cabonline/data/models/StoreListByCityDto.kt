package com.victor.test.cabonline.data.models

/**
 * Created by victorpalmacarrasco on 15/4/18.
 * ${APP_NAME}
 */
data class StoreListByCityDto(
        val regionName:String,
        val expandList:Boolean,
        val storeList:ArrayList<StoreDto>
)