package com.krikey.interview.search.impl

import com.krikey.interview.arch.Transformer
import com.krikey.interview.search.model.SearchBreedPresentation
import com.krikey.interview.search.model.SearchBreedPresentationModel
import com.krikey.interview.search.model.SearchBreedResponse
import javax.inject.Inject

class SearchBreedTransformer @Inject constructor()
    : Transformer<List<SearchBreedResponse>, SearchBreedPresentationModel> {

    override fun transform(input: List<SearchBreedResponse>?): SearchBreedPresentationModel {
        return SearchBreedPresentation(
            arrayListOf<SearchBreedPresentationModel.BreedPresentationModel>().apply {
                input?.forEach {
                    this.add(
                        SearchBreedPresentation.BreedPresentation(
                            it.name ?: "Unknown",
                            it.cfaUrl ?: it.wikipediaUrl.orEmpty()
                        )
                    )
                }
            }
        )
    }


}