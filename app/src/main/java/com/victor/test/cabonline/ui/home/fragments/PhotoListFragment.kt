package com.victor.test.cabonline.ui.home.fragments

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.victor.test.cabonline.R
import com.victor.test.cabonline.data.realm.RealmPhotoDto
import com.victor.test.cabonline.ui.features.SpaceDecorator
import com.victor.test.cabonline.ui.home.adapters.PhotoListAdapter
import com.victor.test.cabonline.utils.getDpFromValue
import io.realm.Realm
import io.realm.RealmList
import kotlinx.android.synthetic.main.fragment_photo_list.*

/**
 * Created by victorpalmacarrasco on 17/4/18.
 * ${APP_NAME}
 */
class PhotoListFragment: Fragment() {
    private lateinit var realm: Realm


    companion object {
        @JvmStatic fun newInstance(): PhotoListFragment = PhotoListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_photo_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        realm = Realm.getDefaultInstance()

        val linearLayoutManager = LinearLayoutManager(view.context)
        lstPhotos.layoutManager = linearLayoutManager

        lstPhotos.addItemDecoration(SpaceDecorator(getDpFromValue(view.context, 10)))

        val photoSource = realm.where(RealmPhotoDto::class.java).findAll()
        val photoList = RealmList<RealmPhotoDto>()
        photoList.addAll(photoSource.subList(0, photoSource.size))

        val photoListAdapter = PhotoListAdapter(photoList)
        lstPhotos.adapter = photoListAdapter

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}