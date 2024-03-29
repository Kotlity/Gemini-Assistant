package com.gemini.assistant.utils

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.unit.sp

object Constants {

    const val DATABASE_NAME = "gemini_search.db"
    const val ERROR = "Something went wrong"
    const val LOADING_MESSAGE = "Please wait while the model generates a response"
    const val UNKNOWN_ERROR = "Unknown error occurred..."

    const val MAX_SEARCH_QUERIES = 5

    const val _500 = 500

    const val _300L = 300L
    const val _100L = 100L
    const val _5000L = 5000L

    val _14sp = 14.sp
    val _16sp = 16.sp
    val _18sp = 18.sp

    const val _0f = 0f
    const val _02f = 0.2f
    const val _02_5f = 0.25f
    const val _05f = 0.5f
    const val _07f = 0.7f
    const val _07_5f = 0.75f
    const val _08f = 0.8f
    const val _1f = 1f
    const val _1_5f = 1.5f

    const val EXTERNAL_STORAGE_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    const val MEDIA_IMAGES_PERMISSION = Manifest.permission.READ_MEDIA_IMAGES
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    const val MEDIA_VISUAL_USER_SELECTED_PERMISSION = Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
}