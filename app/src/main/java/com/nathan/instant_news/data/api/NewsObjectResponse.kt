package com.nathan.instant_news.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.nathan.instant_news.data.model.Source
import java.util.*

/**
 * Object representing a news from the API
 */
data class NewsObjectResponse(
    @SerializedName("source")
    @Expose
    val source: Source,

    @SerializedName("author")

    @Expose
    val author: String?,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("description")
    @Expose
    val description: String?,

    @SerializedName("url")
    @Expose
    val url: String,

    @SerializedName("urlToImage")
    @Expose
    val urlToImage: String?,

    @SerializedName("publishedAt")
    @Expose
    val publishedAt: Date,

    @SerializedName("content")
    @Expose
    val content: String,
)