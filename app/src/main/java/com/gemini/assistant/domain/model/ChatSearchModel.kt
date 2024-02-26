package com.gemini.assistant.domain.model

import com.gemini.assistant.data.room.entities.ChatSearchEntity

data class ChatSearchModel(
    val search: String
) {

    fun toSearchEntity(): ChatSearchEntity {
        return ChatSearchEntity(
            search = search
        )
    }
}
