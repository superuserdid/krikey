package com.krikey.interview.search.model

import java.io.Serializable

interface SearchBreedPresentationModel: Serializable {

    val breeds: List<BreedPresentationModel>

    interface BreedPresentationModel {

        val website: String

        val name: String

    }

}

data class SearchBreedPresentation(override val breeds: List<SearchBreedPresentationModel.BreedPresentationModel>) : SearchBreedPresentationModel {

    data class BreedPresentation(override val name: String,
                                 override val website: String): SearchBreedPresentationModel.BreedPresentationModel

}