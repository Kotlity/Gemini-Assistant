package com.gemini.assistant.data.room.entities

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gemini.assistant.domain.info.SearchInput

@Entity
data class SearchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val search: String,
    val bitmaps: List<Bitmap>? = null
) {

    fun toSearchInput(): SearchInput {
        return SearchInput(
            id = id,
            search = search,
            bitmaps = bitmaps
        )
    }
}