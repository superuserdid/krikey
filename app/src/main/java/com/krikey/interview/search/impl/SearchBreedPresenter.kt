package com.krikey.interview.search.impl

import com.krikey.interview.search.SearchBreedContract
import com.krikey.interview.feed.model.CatFeedPresentationModel
import com.krikey.interview.search.model.SearchBreedPresentationModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class SearchBreedPresenter @Inject constructor(private val interactor: SearchBreedContract.Interactor): SearchBreedContract.Presenter {

    private var view: SearchBreedContract.View? = null

    private val searchInputSubject = BehaviorSubject.create<String>()

    private val disposable = CompositeDisposable()

    override fun onViewAttached(view: SearchBreedContract.View) {
        this.view = view
        disposable.add(searchInputSubject
            .debounce(1, TimeUnit.SECONDS)
            .subscribe {
                performSearch(it)
        })
    }

    private fun performSearch(query: String) {
        interactor.search(query, object: SearchBreedContract.Listener<SearchBreedPresentationModel> {
            override fun onSuccess(response: SearchBreedPresentationModel) {
                view?.onSearchResultsRetrieved(response)
            }

            override fun onError(error: SearchBreedContract.Error) {
                view?.onError(error)
            }
        })
    }

    override fun search(query: String) {
        if (query.isNotEmpty()) {
            searchInputSubject.onNext(query)
        } else {
            view?.clear()
        }
    }

    override fun onBreedClick(breed: SearchBreedPresentationModel.BreedPresentationModel) {
        breed.website.apply {
            if (this.isNotEmpty()) {
                view?.openWebsite(breed.website)
            } else {
                view?.showNoWebsiteToOpen()
            }
        }
    }

    override fun onViewDetached() {
        disposable.clear()
        interactor.onDestroy()
        view = null
    }

}