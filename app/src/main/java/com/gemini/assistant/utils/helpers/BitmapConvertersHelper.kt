package com.gemini.assistant.utils.helpers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri

fun List<String>.convertStringsToBitmaps(context: Context): List<Bitmap> {
    val bitmaps = mutableListOf<Bitmap>()
    return try {
        forEach { stringImage ->
            val uri = Uri.parse(stringImage)
            uri.parseUriToBitmap(context)?.let { bitmap ->
                bitmaps.add(bitmap)
            }
//            context.contentResolver.openInputStream(uri).use {
//                val bitmap = BitmapFactory.decodeStream(it)
//                bitmaps.add(bitmap)
//            }
        }
        bitmaps
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}

fun Uri.parseUriToBitmap(context: Context): Bitmap? {
     return try {
        context.contentResolver.openInputStream(this)?.use { inputStream ->
            BitmapFactory.decodeStream(inputStream)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun Uri.parseUriToString(): String {
    return this.toString()
}

fun String.parseStringToBitmap(context: Context): Bitmap? {
    return try {
        val uri = Uri.parse(this)
        uri.parseUriToBitmap(context)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}