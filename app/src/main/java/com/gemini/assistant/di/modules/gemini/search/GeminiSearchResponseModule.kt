package com.gemini.assistant.di.modules.gemini.search

import com.gemini.assistant.data.gemini_search.GeminiSearchResponse
import com.gemini.assistant.di.modules.gemini.model.GeminiModelModule
import com.gemini.assistant.domain.search.SearchResponse
import com.google.ai.client.generativeai.GenerativeModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module(includes = [GeminiModelModule::class])
@InstallIn(ViewModelComponent::class)
object GeminiSearchResponseModule {

    @Provides
    @ViewModelScoped
    fun provideGeminiSearchResponse(geminiModel: GenerativeModel): SearchResponse = GeminiSearchResponse(geminiModel = geminiModel)
}