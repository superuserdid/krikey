package com.krikey.interview.feed.di

import com.krikey.interview.arch.Transformer
import com.krikey.interview.feed.impl.CatFeedTransformer
import com.krikey.interview.feed.model.CatFeedPresentationModel
import com.krikey.interview.feed.model.CatFeedResponse
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CatFeedDataModule {

    @Provides
    fun provideTransformer(feedTransformer: CatFeedTransformer)
        : Transformer<List<CatFeedResponse>, CatFeedPresentationModel> = feedTransformer

}