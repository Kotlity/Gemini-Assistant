package com.gemini.assistant.di.modules.room.database

import android.content.Context
import androidx.room.Room
import com.gemini.assistant.data.room.converters.ImagesTypeConverter
import com.gemini.assistant.data.room.database.GeminiSearchDatabase
import com.gemini.assistant.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object GeminiSearchDatabaseModule {

    @Provides
    @ViewModelScoped
    fun provideGeminiSearchDatabase(@ApplicationContext context: Context): GeminiSearchDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = GeminiSearchDatabase::class.java,
            name = DATABASE_NAME
        )
            .addTypeConverter(ImagesTypeConverter::class.java)
            .fallbackToDestructiveMigration()
            .build()
    }

}