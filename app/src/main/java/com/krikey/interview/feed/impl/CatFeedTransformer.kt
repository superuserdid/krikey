package com.krikey.interview.feed.impl

import com.krikey.interview.arch.Transformer
import com.krikey.interview.feed.model.CatFeedPresentation
import com.krikey.interview.feed.model.CatFeedPresentationModel
import com.krikey.interview.feed.model.CatFeedResponse
import javax.inject.Inject

class CatFeedTransformer @Inject constructor()
    : Transformer<List<CatFeedResponse>, CatFeedPresentationModel> {

    override fun transform(input: List<CatFeedResponse>?): CatFeedPresentationModel {
        return CatFeedPresentation(
            arrayListOf<CatFeedPresentationModel.ImagePresentationModel>().apply {
                input?.forEach {
                    this.add(
                        CatFeedPresentation.ImagePresentation(
                            it.image
                        )
                    )
                }
            }
        )
    }


}