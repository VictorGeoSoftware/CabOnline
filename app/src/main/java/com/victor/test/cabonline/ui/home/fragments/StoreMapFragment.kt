package com.victor.test.cabonline.ui.home.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.victor.test.cabonline.R
import com.victor.test.cabonline.data.realm.RealmStoreDto
import io.realm.Realm
import io.realm.RealmList


/**
 * Created by victorpalmacarrasco on 17/4/18.
 * ${APP_NAME}
 */
class StoreMapFragment: Fragment(), OnMapReadyCallback {
    private lateinit var realm: Realm
    private lateinit var mapFragment: MapFragment
    private lateinit var storeList: RealmList<RealmStoreDto>

    companion object {
        @JvmStatic fun newInstance(): StoreMapFragment = StoreMapFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mapFragment = MapFragment.newInstance()
        return inflater.inflate(R.layout.fragment_store_map, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        realm = Realm.getDefaultInstance()

        val fragmentTransaction = activity.fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.mapContainer, mapFragment)
        fragmentTransaction.commit()

        mapFragment.getMapAsync(this)

        storeList = RealmList<RealmStoreDto>()
        val storeSource = realm.where(RealmStoreDto::class.java).findAll()
        storeList.addAll(storeSource.subList(0, storeSource.size))
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }



    // -----------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ GOOGLE MAPS INTERFACE ------------------------------------------------------------
    override fun onMapReady(map: GoogleMap?) {
        val latLngMadrid = LatLng(40.4378693, -3.8199658)


        for (store in storeList) {
            map?.addMarker(MarkerOptions().position(LatLng(store?.latitude ?: 0.0, store?.longitude ?: 0.0)))
        }

        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLngMadrid, 10f)
        map?.animateCamera(cameraUpdate)
    }
}