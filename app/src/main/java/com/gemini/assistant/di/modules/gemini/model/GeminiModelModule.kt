package com.gemini.assistant.di.modules.gemini.model

import com.gemini.assistant.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object GeminiModelModule {

    @Provides
    @ViewModelScoped
    fun provideGeminiModel(): GenerativeModel {
        return GenerativeModel(
            modelName = "gemini-pro-vision",
            apiKey = BuildConfig.apiKey,
            safetySettings = listOf(
                SafetySetting(harmCategory = HarmCategory.HARASSMENT, threshold = BlockThreshold.ONLY_HIGH),
                SafetySetting(harmCategory = HarmCategory.HATE_SPEECH, threshold = BlockThreshold.LOW_AND_ABOVE),
                SafetySetting(harmCategory = HarmCategory.DANGEROUS_CONTENT, threshold = BlockThreshold.LOW_AND_ABOVE),
                SafetySetting(harmCategory = HarmCategory.SEXUALLY_EXPLICIT, threshold = BlockThreshold.MEDIUM_AND_ABOVE)
            )
        )
    }
}