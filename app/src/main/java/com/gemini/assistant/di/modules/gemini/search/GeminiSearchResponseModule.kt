package com.gemini.assistant.di.modules.gemini.search

import android.content.Context
import com.gemini.assistant.data.gemini_search.GeminiSearchResponse
import com.gemini.assistant.di.modules.gemini.model.GeminiModelModule
import com.gemini.assistant.domain.search.SearchResponse
import com.google.ai.client.generativeai.GenerativeModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module(includes = [GeminiModelModule::class])
@InstallIn(ViewModelComponent::class)
object GeminiSearchResponseModule {

    @Provides
    @ViewModelScoped
    fun provideGeminiSearchResponse(
        @ApplicationContext context: Context,
        geminiModel: GenerativeModel
    ): SearchResponse =
        GeminiSearchResponse(
            applicationContext = context,
            geminiModel = geminiModel
        )
}