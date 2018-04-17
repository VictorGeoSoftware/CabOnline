package com.victor.test.cabonline.ui.home.fragments

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.victor.test.cabonline.R
import com.victor.test.cabonline.data.realm.RealmBlogDto
import com.victor.test.cabonline.ui.features.SpaceDecorator
import com.victor.test.cabonline.ui.home.adapters.BlogListAdapter
import com.victor.test.cabonline.utils.getDpFromValue
import com.victor.test.cabonline.utils.trace
import io.realm.Realm
import io.realm.RealmList
import kotlinx.android.synthetic.main.fragment_blog_list.*

/**
 * Created by victorpalmacarrasco on 17/4/18.
 * ${APP_NAME}
 */
class BlogListFragment: Fragment() {
    private lateinit var realm: Realm


    companion object {
        @JvmStatic fun newInstance(): BlogListFragment = BlogListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_blog_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        realm = Realm.getDefaultInstance()

        val linearLayoutManager = LinearLayoutManager(view.context)
        lstBlogs.layoutManager = linearLayoutManager

        lstBlogs.addItemDecoration(SpaceDecorator(getDpFromValue(view.context, 10)))


        val blogs = realm.where(RealmBlogDto::class.java).findAll()

        val blogList = RealmList<RealmBlogDto>()
        blogList.addAll(blogs.subList(0, blogs.size))
        trace("onPhotoDataRetrieved - stored photo objects ::${blogList.size} ")

        val blogsAdapter = BlogListAdapter(blogList)
        lstBlogs.adapter = blogsAdapter

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}