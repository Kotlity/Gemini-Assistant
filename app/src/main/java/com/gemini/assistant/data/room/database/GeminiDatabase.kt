package com.gemini.assistant.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gemini.assistant.data.room.converters.ImagesTypeConverter
import com.gemini.assistant.data.room.daos.GeminiChatSearchDao
import com.gemini.assistant.data.room.daos.UserPhotoDao
import com.gemini.assistant.data.room.entities.ChatSearchEntity

@Database(entities = [ChatSearchEntity::class], version = 1)
@TypeConverters(value = [ImagesTypeConverter::class])
abstract class GeminiDatabase: RoomDatabase() {

    abstract val geminiChatSearchDao: GeminiChatSearchDao
    abstract val userPhotoDao: UserPhotoDao
}