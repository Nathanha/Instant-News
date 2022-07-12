package com.nathan.instant_news

import co.infinum.retromock.Retromock
import com.google.gson.GsonBuilder
import com.nathan.instant_news.data.api.NewsApi
import com.nathan.instant_news.data.api.NewsMapper
import com.nathan.instant_news.data.model.News
import com.nathan.instant_news.data.model.Source
import com.nathan.instant_news.data.repository.MainRepository
import com.nathan.instant_news.utils.DataState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.toList
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalCoroutinesApi
@DelicateCoroutinesApi
@RunWith(JUnit4::class)
class MainRepositoryTest {

    private lateinit var mainRepository: MainRepository
    lateinit var newsApi: NewsApi

    @Before
    fun setup() {
        val gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .serializeNulls()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://base")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val retromock = Retromock.Builder()
            .retrofit(retrofit)
            .build()
        newsApi = retromock.create(NewsApi::class.java)
    }

    /**
     * Test MainRepository getNews() method with returned data
     */
    @Test
    fun getNewsTest(): Unit = runBlocking {
        val news = News(
            Source("id", "n"),
            "a",
            "t",
            "d",
            "url",
            "uti",
            SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2022-07-11 08:18:15") ?: Date(),
            "c"
        )

        mainRepository = MainRepository(newsApi, NewsMapper())

        val response = mainRepository.getNews().toList()[1] as DataState.Success<List<News>>

        assertEquals(listOf(news), response.data)
    }
}