package com.nathan.instant_news

import com.google.gson.Gson
import com.nathan.instant_news.data.api.NewsApi
import kotlinx.coroutines.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class NewsApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var newsApi: NewsApi
    private lateinit var gson: Gson

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()
        newsApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(NewsApi::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    /**
     * Test NewsApi getNews() method with no returned data
     */
    @Test
    fun getNewsTest(): Unit = runBlocking {
        val mockResponse = MockResponse()
        mockWebServer.enqueue(mockResponse.setBody("{\"status\": \"ok\",\"totalResults\": 0,\"articles\": [] }"))
        val response = newsApi.getNews("key", "fr", 1)
        val request = mockWebServer.takeRequest()
        assertEquals("/top-headlines?apiKey=key&country=fr&pageSize=1",request.path)
        assertTrue(response.status == "ok")
        assertTrue(response.totalResults == 0)
        assertTrue(response.articles.isEmpty())
    }
}