package com.krikey.interview.search.impl

import com.krikey.interview.feed.di.CatFeedApi
import com.krikey.interview.search.SearchBreedContract
import com.krikey.interview.search.model.SearchBreedResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchBreedRepository @Inject constructor(private val catFeedApi: CatFeedApi) : SearchBreedContract.Repository {

    private val compositeDisposable = CompositeDisposable()

    override fun search(query: String, listener: SearchBreedContract.Listener<List<SearchBreedResponse>>) {
        compositeDisposable.add(catFeedApi.searchBreed(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listener.onSuccess(it)
            }, {
                it.printStackTrace()
                listener.onError(SearchBreedContract.Error.NETWORK_UNAVAILABLE)
            }))
    }

    override fun onDestroy() {
        compositeDisposable.clear()
    }

}
