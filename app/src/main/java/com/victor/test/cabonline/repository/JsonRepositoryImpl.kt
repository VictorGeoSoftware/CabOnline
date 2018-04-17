package com.victor.test.cabonline.repository

import com.google.gson.Gson
import com.victor.test.cabonline.data.BlogRetrievedData
import com.victor.test.cabonline.data.StoreListByCity
import com.victor.test.cabonline.data.models.PhotoDto
import com.victor.test.cabonline.utils.fromJson
import io.reactivex.Observable
import java.io.InputStream

/**
 * Created by victorpalmacarrasco on 15/4/18.
 * ${APP_NAME}
 */
class JsonRepositoryImpl: JsonRepository {

    override fun getStoreList(jsonSource: InputStream): Observable<StoreListByCity> {
        return Observable.create({

            try {
                val inputString = jsonSource.bufferedReader().use{it.readText()}
                val storeListByCity = Gson().fromJson<StoreListByCity>(inputString)
                it.onNext(storeListByCity)
            } catch (e:Exception) {
                e.printStackTrace()
                it.onError(e)
            }

            it.onComplete()
        })
    }

    override fun getPhotoList(jsonSource: InputStream): Observable<ArrayList<PhotoDto>> {
        return Observable.create({

            try {
                val inputString = jsonSource.bufferedReader().use{it.readText()}
                val photoList = Gson().fromJson<ArrayList<PhotoDto>>(inputString)
                it.onNext(photoList)
            } catch (e:Exception) {
                e.printStackTrace()
                it.onError(e)
            }

            it.onComplete()
        })
    }

    override fun getBlogList(jsonSource: InputStream): Observable<BlogRetrievedData> {
        return Observable.create {
            try {
                val inputString = jsonSource.bufferedReader().use{it.readText()}
                val blogRetrievedData = Gson().fromJson<BlogRetrievedData>(inputString)
                it.onNext(blogRetrievedData)
            } catch (e: Exception) {
                e.printStackTrace()
                it.onError(e)
            }

            it.onComplete()
        }
    }
}