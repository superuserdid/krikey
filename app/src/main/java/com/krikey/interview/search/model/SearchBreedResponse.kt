package com.krikey.interview.search.model

import com.google.gson.annotations.SerializedName

data class SearchBreedResponse(@SerializedName("name") val name: String?,
                               @SerializedName("cfa_url") val cfaUrl: String?,
                               @SerializedName("wikipedia_url") val wikipediaUrl: String?)