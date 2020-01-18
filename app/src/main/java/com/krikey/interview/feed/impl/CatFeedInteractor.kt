package com.krikey.interview.feed.impl

import com.krikey.interview.arch.Transformer
import com.krikey.interview.feed.CatFeedContract
import com.krikey.interview.feed.model.CatFeedPresentationModel
import com.krikey.interview.feed.model.CatFeedResponse
import javax.inject.Inject

@Suppress("SpellCheckingInspection")
class CatFeedInteractor @Inject constructor(private val repository: CatFeedContract.Repository,
                                            private val catFeedTransformer: Transformer<List<CatFeedResponse>, CatFeedPresentationModel>)
    : CatFeedContract.Interactor {

    override fun fetchCats(listener: CatFeedContract.Listener<CatFeedPresentationModel>) {
        repository.fetchCats(object : CatFeedContract.Listener<List<CatFeedResponse>> {
            override fun onSuccess(response: List<CatFeedResponse>) {
                listener.onSuccess(catFeedTransformer.transform(response))
            }

            override fun onError(error: CatFeedContract.Error) {
                listener.onError(error)
            }
        })
    }

    override fun onDestroy() {
        repository.onDestroy()
    }

}