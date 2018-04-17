package com.victor.test.cabonline.ui.home.adapters

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.View
import android.view.ViewGroup
import com.victor.test.cabonline.R
import com.victor.test.cabonline.data.realm.RealmBlogDto
import com.victor.test.cabonline.utils.inflate
import io.realm.RealmList
import kotlinx.android.synthetic.main.adapter_blog_list.view.*


/**
 * Created by victorpalmacarrasco on 17/4/18.
 * ${APP_NAME}
 */
class BlogListAdapter(private val blogList: RealmList<RealmBlogDto>): RecyclerView.Adapter<BlogListAdapter.BlogViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        return BlogViewHolder(parent.inflate(R.layout.adapter_blog_list))
    }

    override fun getItemCount(): Int {
        return blogList.size
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        holder.bind(blogList[position])
    }

    class BlogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(realmBlogDto: RealmBlogDto?) = with(itemView) {
            txtTitle.text = realmBlogDto?.title
            txtAuthor.text = realmBlogDto?.author?.displayName

            val dateArray = realmBlogDto?.published?.split("T")
            txtDate.text = dateArray?.get(0)

            val spannedText = Html.fromHtml(realmBlogDto?.content)
            txtContent.text = spannedText

            txtUrl.text = realmBlogDto?.url

            txtUrl.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(realmBlogDto?.url))
                itemView.context.startActivity(intent)
            }
        }
    }
}