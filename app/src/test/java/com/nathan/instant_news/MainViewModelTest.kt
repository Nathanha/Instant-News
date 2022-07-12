package com.nathan.instant_news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nathan.instant_news.data.api.NewsApi
import com.nathan.instant_news.data.api.NewsMapper
import com.nathan.instant_news.data.model.News
import com.nathan.instant_news.data.model.Source
import com.nathan.instant_news.data.repository.MainRepository
import com.nathan.instant_news.ui.viewmodel.MainStateEvent
import com.nathan.instant_news.ui.viewmodel.MainViewModel
import com.nathan.instant_news.utils.DataState
import com.nathan.instant_news.utils.getOrAwaitValue
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.util.*

@ExperimentalCoroutinesApi
@DelicateCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private lateinit var mainRepository: MainRepository
    lateinit var mainViewModel: MainViewModel
    lateinit var newsMapper: NewsMapper

    @Mock
    lateinit var newsApi: NewsApi

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        newsMapper = NewsMapper()
        mainRepository = MainRepository(newsApi, newsMapper)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    /**
     * Test MainViewModel getNews() method with returned data
     */
    @Test
    fun getNewsTest(): Unit = runBlocking {
        val news = News(
            Source("id", "name"),
            "author",
            "title",
            "description",
            "url",
            "urlToTimage",
            Date(),
            "content"
        )

        launch(Dispatchers.Main) {  // Launch in the mainThreadSurrogate dispatcher

            val mock: MainRepository = mock {
                onBlocking { getNews() } doReturn flow {
                    emit(DataState.Success(listOf(news)))
                }
            }
            mainViewModel = MainViewModel(mock)
            mainViewModel.setStateEvent(MainStateEvent.GetNewsEvents)

            val result = mainViewModel.dataState.getOrAwaitValue() as DataState.Success<List<News>>
            assertEquals(listOf(news), result.data)

        }
    }

    /**
     * Test MainViewModel getNews() method with empty returned data
     */
    @Test
    fun `empty news list test`(): Unit = runBlocking {
        launch(Dispatchers.Main) {  // Launch in the mainThreadSurrogate dispatcher

            val mainRepositoryMock: MainRepository = mock {
                onBlocking { getNews() } doReturn flow {
                    emit(DataState.Success(listOf<News>()))
                }
            }
            mainViewModel = MainViewModel(mainRepositoryMock)
            mainViewModel.setStateEvent(MainStateEvent.GetNewsEvents)

            val result = mainViewModel.dataState.getOrAwaitValue() as DataState.Success<List<News>>
            assertEquals(listOf<News>(), result.data)

        }
    }
}