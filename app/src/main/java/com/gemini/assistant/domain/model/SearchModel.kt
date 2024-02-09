package com.gemini.assistant.domain.model

import com.gemini.assistant.data.room.entities.SearchEntity

data class SearchModel(
    val id: Long,
    val search: String,
    val images: List<String>? = null
) {

    fun toSearchEntity(): SearchEntity {
        return SearchEntity(
            id = id,
            search = search,
            images = images
        )
    }
}
