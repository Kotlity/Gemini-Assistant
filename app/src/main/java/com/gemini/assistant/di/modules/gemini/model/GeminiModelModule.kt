package com.gemini.assistant.di.modules.gemini.model

import com.gemini.assistant.BuildConfig
import com.gemini.assistant.di.annotations.GeminiProModel
import com.gemini.assistant.di.annotations.GeminiProVisionModel
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

private const val API_KEY = BuildConfig.apiKey
private val safetySettings = listOf(
    SafetySetting(harmCategory = HarmCategory.HARASSMENT, threshold = BlockThreshold.ONLY_HIGH),
    SafetySetting(harmCategory = HarmCategory.HATE_SPEECH, threshold = BlockThreshold.LOW_AND_ABOVE),
    SafetySetting(harmCategory = HarmCategory.DANGEROUS_CONTENT, threshold = BlockThreshold.LOW_AND_ABOVE),
    SafetySetting(harmCategory = HarmCategory.SEXUALLY_EXPLICIT, threshold = BlockThreshold.MEDIUM_AND_ABOVE)
)

@Module
@InstallIn(ViewModelComponent::class)
object GeminiModelModule {

    @Provides
    @ViewModelScoped
    @GeminiProModel
    fun provideGeminiProModel(): GenerativeModel {
        return GenerativeModel(
            modelName = "gemini-pro",
            apiKey = API_KEY,
            safetySettings = safetySettings
        )
    }

    @Provides
    @ViewModelScoped
    @GeminiProVisionModel
    fun provideGeminiProVisionModel(): GenerativeModel {
        return GenerativeModel(
            modelName = "gemini-pro-vision",
            apiKey = API_KEY,
            safetySettings = safetySettings
        )
    }
}