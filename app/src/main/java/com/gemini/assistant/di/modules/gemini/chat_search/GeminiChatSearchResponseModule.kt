package com.gemini.assistant.di.modules.gemini.chat_search

import com.gemini.assistant.data.dispatchers.AppDispatcher
import com.gemini.assistant.data.gemini_chat_search.GeminiChatSearchResponse
import com.gemini.assistant.di.annotations.GeminiProModel
import com.gemini.assistant.di.modules.dispatcher.AppDispatcherModule
import com.gemini.assistant.di.modules.gemini.model.GeminiModelModule
import com.gemini.assistant.domain.chat_search.ChatSearchResponse
import com.google.ai.client.generativeai.GenerativeModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module(includes = [GeminiModelModule::class, AppDispatcherModule::class])
@InstallIn(ViewModelComponent::class)
object GeminiChatSearchResponseModule {

    @Provides
    @ViewModelScoped
    fun provideGeminiChatSearchResponse(
        @GeminiProModel
        geminiProModel: GenerativeModel,
        appDispatcher: AppDispatcher
    ): ChatSearchResponse =
        GeminiChatSearchResponse(
            geminiProModel = geminiProModel,
            appDispatcher = appDispatcher
        )
}