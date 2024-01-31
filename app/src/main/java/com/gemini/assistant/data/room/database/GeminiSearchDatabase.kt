package com.gemini.assistant.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gemini.assistant.data.room.converters.BitmapTypeConverter
import com.gemini.assistant.data.room.daos.GeminiSearchDao
import com.gemini.assistant.data.room.entities.SearchEntity

@Database(entities = [SearchEntity::class], version = 1)
@TypeConverters(value = [BitmapTypeConverter::class])
abstract class GeminiSearchDatabase: RoomDatabase() {

    abstract val geminiSearchDao: GeminiSearchDao
}