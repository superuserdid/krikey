package com.krikey.interview.arch.impl.cache

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit

@Module
class OkHttpCacheModule {

    private companion object {

        private const val API_KEY = "3f67a51b-b9dd-4363-9e23-86f30e3cda65"

    }

    @Provides
    fun provideOkHttpClient(cacheDirectory: File): OkHttpClient = OkHttpClient.Builder()
        .cache(Cache(cacheDirectory, Long.MAX_VALUE))
        .addNetworkInterceptor { chain ->
            chain.proceed(
                chain.request().newBuilder().apply {
                    header("x-Api-Key", API_KEY)
                }.build()
            )
        }.build()

}