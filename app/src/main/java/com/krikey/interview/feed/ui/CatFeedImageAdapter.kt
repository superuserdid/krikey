package com.krikey.interview.feed.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.krikey.interview.arch.ImageLoader
import com.krikey.interview.feed.model.CatFeedPresentationModel
import kotlinx.android.synthetic.main.cat_feed_item.view.*

class CatFeedImageAdapter(cats: List<CatFeedPresentationModel.ImagePresentationModel>,
                          private val imageLoader: ImageLoader) : RecyclerView.Adapter<CatFeedImageAdapter.CatFeedImageViewHolder>() {

    private var dataSet = arrayListOf<CatFeedPresentationModel.ImagePresentationModel>().apply { addAll(cats) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CatFeedImageViewHolder(LayoutInflater.from(parent.context)
            .inflate(com.krikey.interview.R.layout.cat_feed_item, parent, false))

    override fun onBindViewHolder(holder: CatFeedImageViewHolder, position: Int) {
        val imagePresentation = dataSet[position]
        imageLoader.loadImage(holder.image, imagePresentation.imageUrl)
    }

    override fun getItemCount() = dataSet.count()

    class CatFeedImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val image: ImageView = view.image

    }

}