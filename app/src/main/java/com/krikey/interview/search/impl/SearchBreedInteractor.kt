package com.krikey.interview.search.impl

import com.krikey.interview.arch.Transformer
import com.krikey.interview.feed.CatFeedContract
import com.krikey.interview.feed.model.CatFeedResponse
import com.krikey.interview.feed.model.CatFeedPresentationModel
import com.krikey.interview.search.SearchBreedContract
import com.krikey.interview.search.model.SearchBreedPresentationModel
import com.krikey.interview.search.model.SearchBreedResponse
import javax.inject.Inject

@Suppress("SpellCheckingInspection")
class SearchBreedInteractor @Inject constructor(private val repository: SearchBreedContract.Repository,
                                                private val searchBreedTransformer: Transformer<List<SearchBreedResponse>, SearchBreedPresentationModel>)
    : SearchBreedContract.Interactor {

    override fun search(query: String, listener: SearchBreedContract.Listener<SearchBreedPresentationModel>) {
        repository.search(query, object : SearchBreedContract.Listener<List<SearchBreedResponse>> {
            override fun onSuccess(response: List<SearchBreedResponse>) {
                listener.onSuccess(searchBreedTransformer.transform(response))
            }

            override fun onError(error: SearchBreedContract.Error) {
                listener.onError(error)
            }
        })
    }

    override fun onDestroy() {
        repository.onDestroy()
    }

}