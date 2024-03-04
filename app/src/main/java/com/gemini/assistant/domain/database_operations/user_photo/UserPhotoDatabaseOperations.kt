package com.gemini.assistant.domain.database_operations.user_photo

import com.gemini.assistant.data.room.entities.UserPhotoEntity
import kotlinx.coroutines.flow.Flow

interface UserPhotoDatabaseOperations {

    suspend fun insertUserPhoto(userPhotoEntity: UserPhotoEntity)

    fun retrieveUserPhotoPath(): Flow<String?>
}