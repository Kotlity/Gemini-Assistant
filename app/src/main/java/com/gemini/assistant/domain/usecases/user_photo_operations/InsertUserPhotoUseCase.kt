package com.gemini.assistant.domain.usecases.user_photo_operations

import com.gemini.assistant.data.mappers.toUserPhotoEntity
import com.gemini.assistant.domain.database_operations.user_photo.UserPhotoDatabaseOperations
import com.gemini.assistant.domain.model.UserPhotoModel
import javax.inject.Inject

class InsertUserPhotoUseCase @Inject constructor(private val userPhotoDatabaseOperations: UserPhotoDatabaseOperations) {

    suspend operator fun invoke(userPhotoModel: UserPhotoModel) = userPhotoDatabaseOperations.insertUserPhoto(userPhotoEntity = userPhotoModel.toUserPhotoEntity())
}