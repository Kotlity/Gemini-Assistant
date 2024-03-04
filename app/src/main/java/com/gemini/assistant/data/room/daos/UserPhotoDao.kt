package com.gemini.assistant.data.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gemini.assistant.data.room.entities.UserPhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserPhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserPhoto(userPhotoEntity: UserPhotoEntity)

    @Query("SELECT userPhotoPath FROM userphotoentity LIMIT 1")
    fun retrieveUserPhotoPath(): Flow<String?>
}