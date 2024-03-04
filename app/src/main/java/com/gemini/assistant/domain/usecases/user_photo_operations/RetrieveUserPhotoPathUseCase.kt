package com.gemini.assistant.domain.usecases.user_photo_operations

import com.gemini.assistant.domain.database_operations.user_photo.UserPhotoDatabaseOperations
import javax.inject.Inject

class RetrieveUserPhotoPathUseCase @Inject constructor(private val userPhotoDatabaseOperations: UserPhotoDatabaseOperations) {

    operator fun invoke() = userPhotoDatabaseOperations.retrieveUserPhotoPath()
}