package com.krikey.interview.feed.di

import com.krikey.interview.arch.ImageLoader
import com.krikey.interview.arch.impl.android.AndroidModule
import com.krikey.interview.arch.impl.api.RetrofitApiModule
import com.krikey.interview.arch.impl.cache.OkHttpCacheModule
import com.krikey.interview.arch.impl.imageloader.PicassoImageLoaderModule
import com.krikey.interview.feed.CatFeedContract
import dagger.Component

@Component(
    modules = [
        AndroidModule::class,
        RetrofitApiModule::class,
        OkHttpCacheModule::class,
        PicassoImageLoaderModule::class,
        CatFeedModule::class,
        CatFeedDataModule::class
    ]
)
interface CatFeedComponent {

    val presenter: CatFeedContract.Presenter

    val imageLoader: ImageLoader

}