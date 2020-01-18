package com.krikey.interview.arch.impl.imageloader

import android.widget.ImageView
import com.krikey.interview.arch.ImageLoader
import com.squareup.picasso.Picasso
import javax.inject.Inject

class PicassoImageLoader @Inject constructor(private val picasso: Picasso) : ImageLoader {

    override fun loadImage(imageView: ImageView, url: String) {
        picasso
            .load(url)
            .into(imageView)
    }

}