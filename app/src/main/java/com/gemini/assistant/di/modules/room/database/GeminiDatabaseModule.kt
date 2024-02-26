package com.gemini.assistant.di.modules.room.database

import android.content.Context
import androidx.room.Room
import com.gemini.assistant.data.room.database.GeminiDatabase
import com.gemini.assistant.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object GeminiDatabaseModule {

    @Provides
    @ViewModelScoped
    fun provideGeminiDatabase(@ApplicationContext context: Context): GeminiDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = GeminiDatabase::class.java,
            name = DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}