package com.krikey.interview.feed.di

import com.krikey.interview.feed.model.CatFeedResponse
import com.krikey.interview.search.model.SearchBreedResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CatFeedApi {

    @GET("v1/images/search")
    fun fetchCatFeed(@Query("size") size: String,
                     @Query("limit") limit: Int): Observable<List<CatFeedResponse>>

    @GET("v1/breeds/search")
    fun searchBreed(@Query("q") query: String): Observable<List<SearchBreedResponse>>

}
