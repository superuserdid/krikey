package com.krikey.interview.feed.impl

import com.krikey.interview.feed.CatFeedContract
import com.krikey.interview.feed.di.CatFeedApi
import com.krikey.interview.feed.model.CatFeedResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CatFeedRepository @Inject constructor(private val catFeedApi: CatFeedApi) : CatFeedContract.Repository {

    companion object {

        private const val IMAGE_SIZE = "med"

        private const val RESPONSE_LIMIT = 10

    }

    private val compositeDisposable = CompositeDisposable()

    override fun fetchCats(listener: CatFeedContract.Listener<List<CatFeedResponse>>) {
        compositeDisposable.add(catFeedApi.fetchCatFeed(IMAGE_SIZE, RESPONSE_LIMIT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listener.onSuccess(it)
            }, {
                listener.onError(CatFeedContract.Error.NETWORK_UNAVAILABLE)
            }))
    }

    override fun onDestroy() {
        compositeDisposable.clear()
    }

}
