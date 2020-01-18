package com.krikey.interview.search.di

import com.krikey.interview.arch.impl.android.AndroidModule
import com.krikey.interview.arch.impl.api.RetrofitApiModule
import com.krikey.interview.arch.impl.cache.OkHttpCacheModule
import com.krikey.interview.search.SearchBreedContract
import dagger.Component

@Component(
    modules = [
        AndroidModule::class,
        RetrofitApiModule::class,
        OkHttpCacheModule::class,
        SearchBreedModule::class,
        SearchBreedDataModule::class
    ]
)
interface SearchBreedComponent {

    val presenter: SearchBreedContract.Presenter

}