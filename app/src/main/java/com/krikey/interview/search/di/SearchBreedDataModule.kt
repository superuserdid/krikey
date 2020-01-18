package com.krikey.interview.search.di

import com.krikey.interview.arch.Transformer
import com.krikey.interview.feed.di.CatFeedApi
import com.krikey.interview.feed.impl.CatFeedTransformer
import com.krikey.interview.feed.model.CatFeedPresentationModel
import com.krikey.interview.feed.model.CatFeedResponse
import com.krikey.interview.search.impl.SearchBreedTransformer
import com.krikey.interview.search.model.SearchBreedPresentationModel
import com.krikey.interview.search.model.SearchBreedResponse
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SearchBreedDataModule {

    @Provides
    fun provideTransformer(transformer: SearchBreedTransformer)
        : Transformer<List<SearchBreedResponse>, SearchBreedPresentationModel> = transformer

}