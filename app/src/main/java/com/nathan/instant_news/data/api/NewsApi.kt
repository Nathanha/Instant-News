package com.nathan.instant_news.data.api

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import com.nathan.instant_news.data.api.`object`.APIObjectResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface representing all the possible requests to the API
 */
interface NewsApi {
    @Mock
    @MockResponse(body = "{\"status\": \"ok\",\"totalResults\": 1,\"articles\": [{\"source\": {\"id\": \"id\",\"name\": \"n\" },\"author\": \"a\",\"title\": \"t\",\"description\":\"d\",\"url\": \"url\",\"urlToImage\": \"uti\",\"publishedAt\": \"2022-07-11T06:18:15Z\",\"content\": \"c\" }] }")
    @GET("top-headlines")
    suspend fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
        @Query("pageSize") pageSize: Int
    ): APIObjectResponse
}