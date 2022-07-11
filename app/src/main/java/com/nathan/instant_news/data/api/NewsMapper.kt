package com.nathan.instant_news.data.api

import com.nathan.instant_news.data.model.News
import com.nathan.instant_news.data.model.Source
import com.nathan.instant_news.utils.EntityMapper
import java.util.*
import javax.inject.Inject

/**
 * Class mapping a response of the API to a list of News object
 */
class NewsMapper
@Inject
constructor() : EntityMapper<NewsObjectResponse, News> {

    /**
     * Create a News object from a NewsObjectResponse object
     */
    override fun mapFromEntity(entity: NewsObjectResponse): News {
        val source = Source(
            entity.source.id,
            entity.source.name
        )
        return News(
            source = source,
            author = entity.author,
            title = entity.title,
            description = entity.description,
            url = entity.url,
            urlToImage = entity.urlToImage,
            publishedAt = entity.publishedAt,
            content = entity.content,
        )
    }

    /**
     * Create a list of News objects from an APIObjectResponse object
     */
    fun mapFromResponse(entity: APIObjectResponse): List<News> {
        return entity.articles.map { mapFromEntity(it) }
    }
}
