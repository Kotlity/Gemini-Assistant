package com.gemini.assistant.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gemini.assistant.domain.model.SearchModel

@Entity
data class SearchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val search: String,
    val images: List<String>? = null
) {

    fun toSearchModel(): SearchModel {
        return SearchModel(
            id = id,
            search = search,
            images = images
        )
    }
}