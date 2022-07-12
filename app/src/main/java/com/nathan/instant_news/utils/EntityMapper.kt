package com.nathan.instant_news.utils

/**
 * Interface used by the NewsMapper to map API data to App data
 */
interface EntityMapper<Entity, DomainModel> {
    fun mapFromEntity(entity: Entity): DomainModel
}