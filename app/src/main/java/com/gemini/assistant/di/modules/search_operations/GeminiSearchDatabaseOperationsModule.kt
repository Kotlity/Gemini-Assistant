package com.gemini.assistant.di.modules.search_operations

import com.gemini.assistant.data.gemini_search_database_operations.GeminiSearchDatabaseOperations
import com.gemini.assistant.data.room.daos.GeminiSearchDao
import com.gemini.assistant.di.modules.room.daos.GeminiSearchDaoModule
import com.gemini.assistant.domain.database_operations.SearchDatabaseOperations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module(includes = [GeminiSearchDaoModule::class])
@InstallIn(ViewModelComponent::class)
object GeminiSearchDatabaseOperationsModule {

    @Provides
    @ViewModelScoped
    fun provideGeminiSearchDatabaseOperations(geminiSearchDao: GeminiSearchDao): SearchDatabaseOperations = GeminiSearchDatabaseOperations(geminiSearchDao = geminiSearchDao)
}