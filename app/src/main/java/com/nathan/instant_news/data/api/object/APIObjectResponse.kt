package com.nathan.instant_news.data.api.`object`

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Object representing a response from the API
 */
data class APIObjectResponse(
    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("totalResults")
    @Expose
    val totalResults: Int,

    @SerializedName("articles")
    @Expose
    val articles: List<NewsObjectResponse>,
)