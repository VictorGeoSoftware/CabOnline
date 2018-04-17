package com.victor.test.cabonline.ui.home.adapters

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.victor.test.cabonline.BuildConfig
import com.victor.test.cabonline.R
import com.victor.test.cabonline.data.realm.RealmPhotoDto
import com.victor.test.cabonline.utils.inflate
import com.victor.test.cabonline.utils.trace
import io.realm.RealmList
import kotlinx.android.synthetic.main.adapter_photo_list.view.*

/**
 * Created by victorpalmacarrasco on 17/4/18.
 * ${APP_NAME}
 */

class PhotoListAdapter(private val photoList: RealmList<RealmPhotoDto>): RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(parent.inflate(R.layout.adapter_photo_list))
    }

    override fun getItemCount(): Int = photoList.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photoList[position])
    }



    class PhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(realmPhotoDto: RealmPhotoDto?) = with(itemView) {

            txtAuthor.text = realmPhotoDto?.author
            txtAuthorUrl.text = realmPhotoDto?.author_url

            val photoUrl = BuildConfig.PHOTO_URL_PATH + realmPhotoDto?.filename
            Picasso.with(itemView.context).load(photoUrl).into(imgPhoto)

            txtAuthorUrl.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(realmPhotoDto?.author_url))
                itemView.context.startActivity(intent)
            }
        }
    }
}