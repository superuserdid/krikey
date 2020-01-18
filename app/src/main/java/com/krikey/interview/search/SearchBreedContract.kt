package com.krikey.interview.search

import com.krikey.interview.search.model.SearchBreedPresentationModel
import com.krikey.interview.search.model.SearchBreedResponse

interface SearchBreedContract {

    interface View {

        fun onSearchResultsRetrieved(results: SearchBreedPresentationModel)

        fun openWebsite(url: String)

        fun clear()

        fun onError(error: Error)

        fun showNoWebsiteToOpen()

    }

    interface Presenter {

        fun onViewAttached(view: View)

        fun search(query: String)

        fun onBreedClick(breed: SearchBreedPresentationModel.BreedPresentationModel)

        fun onViewDetached()

    }

    //No Need for Interactor

    interface Interactor {

        fun search(query: String, listener: Listener<SearchBreedPresentationModel>)

        fun onDestroy()

    }

    interface Repository {

        fun search(query: String, listener: Listener<List<SearchBreedResponse>>)

        fun onDestroy()

    }

    //No Need for Repository

    interface Listener<T> {

        fun onSuccess(response: T)

        fun onError(error: Error)

    }

    enum class Error {

        NETWORK_UNAVAILABLE

    }
}