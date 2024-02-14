package com.gemini.assistant.data.room.converters

import androidx.room.TypeConverter

class ImagesTypeConverter {

//    @TypeConverter
//    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
//        val byteArrayOutputStream = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
//        return byteArrayOutputStream.toByteArray()
//    }
//
//    @TypeConverter
//    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
//        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
//    }

    @TypeConverter
    fun toImages(image: String): List<String> {
        return if (image.isNotBlank()) image.split(',').map { it.trim() }
            else emptyList()

    }

    @TypeConverter
    fun toImage(images: List<String>): String {
        return if (images.isNotEmpty()) images.joinToString(",")
            else ""

    }

//    @TypeConverter
//    fun bitmapToString(bitmap: Bitmap): String {
//        val byteArrayOutputStream = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArrayOutputStream)
//        val byteArray = byteArrayOutputStream.toByteArray()
//        val stringRepresentation = Base64.encodeToString(byteArray, Base64.DEFAULT)
//        return stringRepresentation
//    }
//
//    @TypeConverter
//    fun stringToBitmap(encodedString: String): Bitmap {
//        val byteArray = Base64.decode(encodedString, Base64.DEFAULT)
//        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
//        return bitmap
//    }
}