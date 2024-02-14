package com.gemini.assistant.di.modules.dispatcher

import com.gemini.assistant.data.dispatchers.AppDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AppDispatcherModule {

    @Provides
    @ViewModelScoped
    fun provideAppDispatcher(): AppDispatcher = AppDispatcher()
}