package com.gemini.assistant.di.modules.user_photo_operations

import com.gemini.assistant.data.dispatchers.AppDispatcher
import com.gemini.assistant.data.room.daos.UserPhotoDao
import com.gemini.assistant.data.user_photo_database_operations_implementation.UserPhotoDatabaseOperationsImplementation
import com.gemini.assistant.di.modules.dispatcher.AppDispatcherModule
import com.gemini.assistant.di.modules.room.daos.UserPhotoDaoModule
import com.gemini.assistant.domain.database_operations.user_photo.UserPhotoDatabaseOperations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module(includes = [UserPhotoDaoModule::class, AppDispatcherModule::class])
@InstallIn(ViewModelComponent::class)
object UserPhotoDatabaseOperationsModule {

    @Provides
    @ViewModelScoped
    fun provideUserPhotoDatabaseOperations(
        userPhotoDao: UserPhotoDao,
        appDispatcher: AppDispatcher
    ): UserPhotoDatabaseOperations = UserPhotoDatabaseOperationsImplementation(
        userPhotoDao = userPhotoDao,
        appDispatcher = appDispatcher
    )
}