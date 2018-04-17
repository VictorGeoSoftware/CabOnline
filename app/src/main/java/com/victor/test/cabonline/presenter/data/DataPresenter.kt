package com.victor.test.cabonline.presenter.data

import com.victor.test.cabonline.data.BlogRetrievedData
import com.victor.test.cabonline.data.StoreListByCity
import com.victor.test.cabonline.data.models.PhotoDto
import com.victor.test.cabonline.presenter.Presenter
import com.victor.test.cabonline.repository.JsonRepository
import com.victor.test.cabonline.utils.trace
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import java.io.InputStream

/**
 * Created by victorpalmacarrasco on 15/4/18.
 * ${APP_NAME}
 */
class DataPresenter(
        private val androidSchedulers: Scheduler,
        private val subscriberSchedulers: Scheduler,
        private val jsonRepository: JsonRepository): Presenter<DataPresenter.DataView>() {


    private lateinit var disposable: Disposable



    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- VIEW INTERFACE ---------------------------------------------
    interface DataView {
        fun onStoreDataRetrieved(storeListByCity: StoreListByCity) {

        }

        fun onPhotoDataRetrieved(photoList: ArrayList<PhotoDto>) {

        }

        fun onBlogDataRetrieved(blogRetrievedData: BlogRetrievedData) {

        }

        fun onRetrievingDataError() {

        }

        fun showProgressAnimation(show: Boolean) {

        }
    }



    // -------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- PRESENTER METHODS ---------------------------------------------
    fun loadAllData(storeSource: InputStream, photoSource: InputStream, blogSource: InputStream) {
//        val jsonRepository = JsonRepositoryImpl()  /* Real test */

        view?.showProgressAnimation(true)

        val storeObservable = jsonRepository.getStoreList(storeSource)
        val photoObservable = jsonRepository.getPhotoList(photoSource)
        val blogObservable = jsonRepository.getBlogList(blogSource)

        Observable.merge(storeObservable, photoObservable, blogObservable)
                .observeOn(androidSchedulers)
                .subscribeOn(subscriberSchedulers)
                .subscribe(
                        {
//                            trace("onNext :: $it")
                            when (it) {
                                is StoreListByCity -> view?.onStoreDataRetrieved(it)
                                is ArrayList<*> -> {
                                    @Suppress("UNCHECKED_CAST")
                                    view?.onPhotoDataRetrieved(it as ArrayList<PhotoDto>)
                                }
                                is BlogRetrievedData -> view?.onBlogDataRetrieved(it)
                            }
                        },
                        {
                            trace("onError :: $it")
                            view?.onRetrievingDataError()
                            view?.showProgressAnimation(false)
                        },
                        {
                            trace("onFinish!")
                            view?.showProgressAnimation(false)
                        }
                )
    }

    override fun destroy() {
        if (disposable.isDisposed) {
            disposable.dispose()
        }
    }




}