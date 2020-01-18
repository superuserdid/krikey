package com.krikey.interview.arch

import android.widget.ImageView

interface ImageLoader {

    fun loadImage(imageView: ImageView, url: String)

}