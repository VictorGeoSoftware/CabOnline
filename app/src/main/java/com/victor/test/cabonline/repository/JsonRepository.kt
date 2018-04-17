package com.victor.test.cabonline.repository

import com.victor.test.cabonline.data.BlogRetrievedData
import com.victor.test.cabonline.data.StoreListByCity
import com.victor.test.cabonline.data.models.PhotoDto
import io.reactivex.Observable
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by victorpalmacarrasco on 15/4/18.
 * ${APP_NAME}
 */
interface JsonRepository {
    fun getStoreList(jsonSource: InputStream): Observable<StoreListByCity>
    fun getPhotoList(jsonSource: InputStream): Observable<ArrayList<PhotoDto>>
    fun getBlogList(jsonSource: InputStream): Observable<BlogRetrievedData>
}