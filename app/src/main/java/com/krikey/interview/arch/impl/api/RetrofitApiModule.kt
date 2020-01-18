package com.krikey.interview.arch.impl.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.krikey.interview.feed.di.CatFeedApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitApiModule {

    private companion object {

        const val END_POINT = "https://api.thecatapi.com/"

    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(END_POINT)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    fun provideApi(retrofit: Retrofit): CatFeedApi = retrofit.create(CatFeedApi::class.java)


}