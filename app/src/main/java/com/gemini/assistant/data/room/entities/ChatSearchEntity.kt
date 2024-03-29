package com.gemini.assistant.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChatSearchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val search: String
)