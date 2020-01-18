package com.krikey.interview.feed.di

import com.krikey.interview.feed.CatFeedContract
import com.krikey.interview.feed.impl.CatFeedInteractor
import com.krikey.interview.feed.impl.CatFeedPresenter
import com.krikey.interview.feed.impl.CatFeedRepository
import dagger.Module
import dagger.Provides

@Module
class CatFeedModule {

    @Provides
    fun providePresenter(presenter: CatFeedPresenter)
        : CatFeedContract.Presenter = presenter

    @Provides
    fun provideInteractor(interactor: CatFeedInteractor)
        : CatFeedContract.Interactor = interactor

    @Provides
    fun provideRepository(repository: CatFeedRepository)
        : CatFeedContract.Repository = repository

}