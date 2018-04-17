package com.victor.test.cabonline.ui.intro

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.victor.test.cabonline.R
import com.victor.test.cabonline.data.BlogRetrievedData
import com.victor.test.cabonline.data.StoreListByCity
import com.victor.test.cabonline.data.models.PhotoDto
import com.victor.test.cabonline.data.realm.*
import com.victor.test.cabonline.presenter.data.DataPresenter
import com.victor.test.cabonline.ui.home.HomeActivity
import com.victor.test.cabonline.utils.app
import com.victor.test.cabonline.utils.trace
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_intro.*
import javax.inject.Inject

/**
 * Created by victorpalmacarrasco on 15/4/18.
 * ${APP_NAME}
 */
class IntroActivity: Activity(), DataPresenter.DataView {
    private val component by lazy { app.component.plus(IntroActivityModule(this)) }
    @Inject lateinit var dataPresenter: DataPresenter
    private lateinit var realm: Realm


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        component.inject(this)

        dataPresenter.view = this


        dataPresenter.loadAllData(
                resources.openRawResource(R.raw.store_list),
                resources.openRawResource(R.raw.photo_list),
                resources.openRawResource(R.raw.blogs)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        dataPresenter.destroy()
    }



    // ---------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ DATA VIEW INTERFACE ------------------------------------------------------------
    override fun showProgressAnimation(show: Boolean) {
        if (show) {
            txtLoadingData.visibility = View.VISIBLE
            progressBar.visibility = View.VISIBLE
        } else {
            txtLoadingData.visibility = View.INVISIBLE
            progressBar.visibility = View.INVISIBLE

            Handler().postDelayed(
                    {
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                    }, 1000
            )
        }
    }

    override fun onStoreDataRetrieved(storeListByCity: StoreListByCity) {
        realm = Realm.getDefaultInstance()

        try {
            realm.executeTransaction {

                storeListByCity.stores.forEach { province, storesByProvince ->

                    for (store in storesByProvince.storeList) {

                        val realmStore = RealmStoreDto(
                                store.id,
                                store.code,
                                store.label,
                                store.street,
                                store.city,
                                store.zipCode,
                                store.province,
                                store.country,
                                store.latitude,
                                store.longitude,
                                store.phoneNumber,
                                store.email
                        )

                        it.insertOrUpdate(realmStore)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            val totalObjects = realm.where(RealmStoreDto::class.java).findAll()
            trace("onStoreDataRetrieved - stored at Realm :: ${totalObjects.size}")
            realm.close()
        }
    }

    override fun onPhotoDataRetrieved(photoList: ArrayList<PhotoDto>) {
        realm = Realm.getDefaultInstance()

        try {
            realm.executeTransaction{
                for (photo in photoList) {

                    val realmPhoto = RealmPhotoDto(
                            photo.id,
                            photo.format,
                            photo.width,
                            photo.height,
                            photo.filename,
                            photo.author,
                            photo.author_url,
                            photo.post_url
                    )

                    it.insertOrUpdate(realmPhoto)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            val totalObjects = realm.where(RealmPhotoDto::class.java).findAll()
            trace("onPhotoDataRetrieved - stored photo objects ::${totalObjects.size} ")
            realm.close()
        }
    }

    override fun onBlogDataRetrieved(blogRetrievedData: BlogRetrievedData) {
        realm = Realm.getDefaultInstance()

        try {
            realm.executeTransaction {

                for (blog in blogRetrievedData.items) {

                    val realmImageDto = RealmImageDto(blog.author.image.url)

                    val realmAuthorDto = RealmAuthorDto(
                            blog.author.id,
                            blog.author.displayName,
                            blog.author.url,
                            realmImageDto
                    )

                    val realmBlog = RealmBlogDto(
                            blog.id,
                            blog.kind,
                            blog.published,
                            blog.updated,
                            blog.etag,
                            blog.url,
                            blog.selfLink,
                            blog.title,
                            blog.content,
                            realmAuthorDto
                    )

                    it.insertOrUpdate(realmBlog)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            val totalObjects = realm.where(RealmBlogDto::class.java).findAll()
            trace("onBlogDataRetrieved - stored blog objects ::${totalObjects.size} ")
            realm.close()

            // TODO :: lanzar a la HomeActivity
        }
    }

    override fun onRetrievingDataError() {
        trace("onDataRetrievingError!")
    }

}