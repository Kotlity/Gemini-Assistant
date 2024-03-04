package com.gemini.assistant.data.user_photo_database_operations_implementation

import com.gemini.assistant.data.dispatchers.AppDispatcher
import com.gemini.assistant.data.room.daos.UserPhotoDao
import com.gemini.assistant.data.room.entities.UserPhotoEntity
import com.gemini.assistant.domain.database_operations.user_photo.UserPhotoDatabaseOperations
import com.gemini.assistant.utils.helpers.onCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserPhotoDatabaseOperationsImplementation @Inject constructor(
    private val userPhotoDao: UserPhotoDao,
    private val appDispatcher: AppDispatcher
): UserPhotoDatabaseOperations {

    override suspend fun insertUserPhoto(userPhotoEntity: UserPhotoEntity) {
        onCoroutineContext {
            userPhotoDao.insertUserPhoto(userPhotoEntity = userPhotoEntity)
        }
    }

    override fun retrieveUserPhotoPath(): Flow<String?> {
        return userPhotoDao
            .retrieveUserPhotoPath()
            .flowOn(appDispatcher.io)
    }
}