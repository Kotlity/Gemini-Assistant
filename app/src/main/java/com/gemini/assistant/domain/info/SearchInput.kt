package com.gemini.assistant.domain.info

import android.graphics.Bitmap
import com.gemini.assistant.data.room.entities.SearchEntity

data class SearchInput(
    val id: Long,
    val search: String,
    val bitmaps: List<Bitmap>? = null
) {

    fun toSearchEntity(): SearchEntity {
        return SearchEntity(
            id = id,
            search = search,
            bitmaps = bitmaps
        )
    }
}
