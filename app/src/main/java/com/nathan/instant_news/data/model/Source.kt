package com.nathan.instant_news.data.model

import java.io.Serializable

/**
 * Represent the Source of a News object
 */
data class Source(
    val id: String?,
    val name: String
) : Serializable
