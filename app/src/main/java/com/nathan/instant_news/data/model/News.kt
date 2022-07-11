package com.nathan.instant_news.data.model

import java.util.*

/**
 * Represent a News object
 */
data class News(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: Date,
    val content: String?,
)