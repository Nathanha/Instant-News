package com.nathan.instant_news.data.api.`object`

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Object representing a news from the API
 */
data class NewsSourceObjectResponse(
    @SerializedName("id")
    @Expose
    val id: String?,

    @SerializedName("name")
    @Expose
    val name: String,
)