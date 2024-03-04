package com.gemini.assistant.data.mappers

import com.gemini.assistant.data.room.entities.ChatSearchEntity
import com.gemini.assistant.data.room.entities.UserPhotoEntity
import com.gemini.assistant.domain.model.ChatSearchModel
import com.gemini.assistant.domain.model.UserPhotoModel

fun ChatSearchEntity.toSearchModel(): ChatSearchModel {
    return ChatSearchModel(
        search = search
    )
}

fun ChatSearchModel.toSearchEntity(): ChatSearchEntity {
    return ChatSearchEntity(
        search = search
    )
}

fun UserPhotoEntity.toUserPhotoModel(): UserPhotoModel {
    return UserPhotoModel(
        userPhotoPath = userPhotoPath
    )
}

fun UserPhotoModel.toUserPhotoEntity(): UserPhotoEntity {
    return UserPhotoEntity(
        userPhotoPath = userPhotoPath
    )
}