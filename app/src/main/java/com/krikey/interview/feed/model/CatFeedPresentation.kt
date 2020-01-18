package com.krikey.interview.feed.model

import java.io.Serializable

interface CatFeedPresentationModel: Serializable {

    val catImages: List<ImagePresentationModel>

    interface ImagePresentationModel {

        val imageUrl: String

    }

}

data class CatFeedPresentation(override val catImages: List<CatFeedPresentationModel.ImagePresentationModel>)
    : CatFeedPresentationModel {

    data class ImagePresentation(override val imageUrl: String): CatFeedPresentationModel.ImagePresentationModel
    
}

