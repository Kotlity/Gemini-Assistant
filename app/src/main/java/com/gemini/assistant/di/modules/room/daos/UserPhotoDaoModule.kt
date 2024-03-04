package com.gemini.assistant.di.modules.room.daos

import com.gemini.assistant.data.room.daos.UserPhotoDao
import com.gemini.assistant.data.room.database.GeminiDatabase
import com.gemini.assistant.di.modules.room.database.GeminiDatabaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module(includes = [GeminiDatabaseModule::class])
@InstallIn(ViewModelComponent::class)
object UserPhotoDaoModule {

    @Provides
    @ViewModelScoped
    fun provideUserPhotoDao(geminiDatabase: GeminiDatabase): UserPhotoDao = geminiDatabase.userPhotoDao
}