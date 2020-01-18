package com.krikey.interview.feed.impl

import com.krikey.interview.feed.CatFeedContract
import com.krikey.interview.feed.model.CatFeedPresentationModel
import javax.inject.Inject

class CatFeedPresenter @Inject constructor(@Suppress("SpellCheckingInspection") private val interactor: CatFeedContract.Interactor)
    : CatFeedContract.Presenter {

    private var view: CatFeedContract.View? = null

    override fun onViewAttached(view: CatFeedContract.View) {
        this.view = view
    }

    override fun fetchCats() {
        interactor.fetchCats(object : CatFeedContract.Listener<CatFeedPresentationModel> {
            override fun onSuccess(response: CatFeedPresentationModel) {
                view?.onCatsRetrieved(response)
            }

            override fun onError(error: CatFeedContract.Error) {
                view?.onError(error)
            }
        })
    }

    override fun onSearchClicked() {
        view?.goToSearch()
    }

    override fun onViewDetached() {
        view = null
        interactor.onDestroy()
    }

}