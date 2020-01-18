package com.krikey.interview.search.impl

import com.krikey.interview.search.SearchBreedContract
import com.krikey.interview.feed.model.CatFeedPresentationModel
import com.krikey.interview.search.model.SearchBreedPresentationModel
import javax.inject.Inject

class SearchBreedPresenter @Inject constructor(private val interactor: SearchBreedContract.Interactor): SearchBreedContract.Presenter {

    private var view: SearchBreedContract.View? = null

    override fun onViewAttached(view: SearchBreedContract.View) {
        this.view = view
    }

    override fun search(query: String) {
        if (query.isNotEmpty()) {
            interactor.search(query, object: SearchBreedContract.Listener<SearchBreedPresentationModel> {
                override fun onSuccess(response: SearchBreedPresentationModel) {
                    view?.onSearchResultsRetrieved(response)
                }

                override fun onError(error: SearchBreedContract.Error) {
                    view?.onError(error)
                }
            })
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
        interactor.onDestroy()
        view = null
    }

}