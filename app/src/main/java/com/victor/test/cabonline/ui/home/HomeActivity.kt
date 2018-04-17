package com.victor.test.cabonline.ui.home

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.victor.test.cabonline.R
import com.victor.test.cabonline.data.Constants
import com.victor.test.cabonline.ui.home.fragments.BlogListFragment
import com.victor.test.cabonline.ui.home.fragments.PhotoListFragment
import com.victor.test.cabonline.ui.home.fragments.StoreMapFragment
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Created by victorpalmacarrasco on 15/4/18.
 * ${APP_NAME}
 */
class HomeActivity: Activity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        loadBlogListFragment()
        bottomNav.setOnNavigationItemSelectedListener(this)
    }



    // ------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ USER INTERACTION ------------------------------------------------------------
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_blog -> {
                loadBlogListFragment()
            }

            R.id.navigation_photos -> {
                loadPhotoListFragment()
            }

            R.id.navigation_stores -> {
                loadStoreMap()
            }
        }

        return true
    }



    // ------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ METHODS AND RUNNABLES -------------------------------------------------------
    private fun loadBlogListFragment() {
        fragmentManager.beginTransaction()
                .replace(
                        R.id.fragmentContainer,
                        BlogListFragment.newInstance(),
                        Constants.FRAGMENT_BLOG_LIST
                )
                .commit()
    }

    private fun loadPhotoListFragment() {
        fragmentManager.beginTransaction()
                .replace(
                        R.id.fragmentContainer,
                        PhotoListFragment.newInstance(),
                        Constants.FRAGMENT_PHOTO_LIST
                )
                .commit()
    }

    private fun loadStoreMap() {
        fragmentManager.beginTransaction()
                .replace(
                        R.id.fragmentContainer,
                        StoreMapFragment.newInstance(),
                        Constants.FRAGMENT_STORE_LIST
                )
                .commit()
    }
}