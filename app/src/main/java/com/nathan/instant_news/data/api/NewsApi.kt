package com.nathan.instant_news.data.api

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface representing all the possible requests to the API
 */
interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String,
        @Query("language") language: String
    ): APIObjectResponse
}