package com.krikey.interview.search.di

import com.krikey.interview.search.SearchBreedContract
import com.krikey.interview.search.impl.SearchBreedInteractor
import com.krikey.interview.search.impl.SearchBreedPresenter
import com.krikey.interview.search.impl.SearchBreedRepository
import dagger.Module
import dagger.Provides

@Module
class SearchBreedModule {

    @Provides
    fun providePresenter(presenter: SearchBreedPresenter)
        : SearchBreedContract.Presenter = presenter

    @Provides
    fun provideRepository(repository: SearchBreedRepository)
        : SearchBreedContract.Repository = repository

    @Provides
    fun provideInteractor(interactor: SearchBreedInteractor)
        : SearchBreedContract.Interactor = interactor

}