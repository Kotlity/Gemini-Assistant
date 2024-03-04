package com.gemini.assistant.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserPhotoEntity(
    @PrimaryKey val id: Int = 0,
    val userPhotoPath: String
)