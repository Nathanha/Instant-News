package com.nathan.instant_news.data.api

import com.nathan.instant_news.data.api.`object`.APIObjectResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface representing all the possible requests to the API
 */
interface NewsApi {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
        @Query("pageSize") pageSize: Int
    ): APIObjectResponse
}