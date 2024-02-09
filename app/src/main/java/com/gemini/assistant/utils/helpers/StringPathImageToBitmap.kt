package com.gemini.assistant.utils.helpers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri

fun List<String>.convertStringsToBitmaps(context: Context): List<Bitmap> {
    val bitmaps = mutableListOf<Bitmap>()
    forEach { stringImage ->
        val uri = Uri.parse(stringImage)
        context.contentResolver.openInputStream(uri).use {
            val bitmap = BitmapFactory.decodeStream(it)
            bitmaps.add(bitmap)
        }
    }
    return bitmaps
}