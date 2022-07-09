package com.nathan.instant_news.data.repository

import com.nathan.instant_news.data.api.NewsApi
import com.nathan.instant_news.data.api.NewsMapper
import com.nathan.instant_news.data.model.News
import com.nathan.instant_news.utils.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Class who abstracts the data source out of the view model
 * Here, we have one data source, but we can add a local database in the future, for example, if we want
 */
class MainRepository(private val newsApi: NewsApi, private val newsMapper: NewsMapper) {

    /**
     * Get News data from NewsAPI
     */
    suspend fun getNews(): Flow<DataState<List<News>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkNews = newsApi.getNews("le-monde", "b1becf42d5c64a5c9e55f3f18c502725", "fr")
            val news = newsMapper.mapFromResponse(networkNews)
            emit(DataState.Success(news))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

}