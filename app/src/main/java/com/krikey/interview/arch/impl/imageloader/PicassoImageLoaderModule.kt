package com.krikey.interview.arch.impl.imageloader

import android.content.Context
import com.krikey.interview.arch.ImageLoader
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class PicassoImageLoaderModule {

    @Provides
    fun providesPicasso(context: Context, client: OkHttpClient)
        : Picasso = Picasso.Builder(context).downloader(OkHttp3Downloader(client)).build()

    @Provides
    fun providesPicassoImageLoader(imageloader: PicassoImageLoader): ImageLoader = imageloader

}