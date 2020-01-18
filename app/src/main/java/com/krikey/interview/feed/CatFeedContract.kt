package com.krikey.interview.feed

import com.krikey.interview.feed.model.CatFeedPresentationModel
import com.krikey.interview.feed.model.CatFeedResponse

interface CatFeedContract {

    interface View {

        fun onCatsRetrieved(catFeed: CatFeedPresentationModel)

        fun goToSearch()

        fun onError(error: Error)

    }

    interface Presenter {

        fun onViewAttached(view: View)

        fun onViewDetached()

        fun fetchCats()

        fun onSearchClicked()

    }

    @Suppress("SpellCheckingInspection")
    interface Interactor {

        fun fetchCats(listener: Listener<CatFeedPresentationModel>)

        fun onDestroy()

    }

    interface Repository {

        fun fetchCats(listener: Listener<List<CatFeedResponse>>)

        fun onDestroy()

    }

    interface Listener<T> {

        fun onSuccess(response: T)

        fun onError(error: Error)

    }

    enum class Error {

        NETWORK_UNAVAILABLE

    }

}