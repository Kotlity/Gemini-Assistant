package com.gemini.assistant.di.modules.room.daos

import com.gemini.assistant.data.room.daos.GeminiSearchDao
import com.gemini.assistant.data.room.database.GeminiSearchDatabase
import com.gemini.assistant.di.modules.room.database.GeminiSearchDatabaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module(includes = [GeminiSearchDatabaseModule::class])
@InstallIn(ViewModelComponent::class)
object GeminiSearchDaoModule {

    @Provides
    @ViewModelScoped
    fun provideGeminiSearchDao(geminiSearchDatabase: GeminiSearchDatabase): GeminiSearchDao = geminiSearchDatabase.geminiSearchDao
}