package com.gemini.assistant.di.modules.chat_search_operations

import com.gemini.assistant.data.dispatchers.AppDispatcher
import com.gemini.assistant.data.gemini_chat_search_database_operations.GeminiChatSearchDatabaseOperations
import com.gemini.assistant.data.room.daos.GeminiChatSearchDao
import com.gemini.assistant.di.modules.dispatcher.AppDispatcherModule
import com.gemini.assistant.di.modules.room.daos.GeminiChatSearchDaoModule
import com.gemini.assistant.domain.database_operations.ChatSearchDatabaseOperations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module(includes = [GeminiChatSearchDaoModule::class, AppDispatcherModule::class])
@InstallIn(ViewModelComponent::class)
object GeminiChatSearchDatabaseOperationsModule {

    @Provides
    @ViewModelScoped
    fun provideGeminiSearchDatabaseOperations(
        geminiChatSearchDao: GeminiChatSearchDao,
        appDispatcher: AppDispatcher
    ): ChatSearchDatabaseOperations = GeminiChatSearchDatabaseOperations(
        geminiChatSearchDao = geminiChatSearchDao,
        appDispatcher = appDispatcher
    )
}