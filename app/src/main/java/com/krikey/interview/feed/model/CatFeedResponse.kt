package com.krikey.interview.feed.model

import com.google.gson.annotations.SerializedName

data class CatFeedResponse(@SerializedName("url") val image: String)