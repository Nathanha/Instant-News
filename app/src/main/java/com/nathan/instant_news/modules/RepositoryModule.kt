package com.nathan.instant_news.modules

import com.nathan.instant_news.data.api.NewsApi
import com.nathan.instant_news.data.api.NewsMapper
import com.nathan.instant_news.data.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module initiating repositories and injecting them dependencies
 */
@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    /**
     * Initiating MainRepository
     */
    @Singleton
    @Provides
    fun provideMainRepository(
        newsApi: NewsApi,
        newsMapper: NewsMapper,
    ): MainRepository {
        return MainRepository(newsApi, newsMapper)
    }

}