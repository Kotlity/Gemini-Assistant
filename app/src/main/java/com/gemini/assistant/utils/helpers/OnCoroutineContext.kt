package com.gemini.assistant.utils.helpers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import kotlin.coroutines.CoroutineContext

suspend inline fun <T> onCoroutineContext(
    coroutineContext: CoroutineContext = Dispatchers.IO,
    noinline block: suspend CoroutineScope.() -> T
): T? {
    return try {
        withContext(coroutineContext, block)
    } catch (io: IOException) {
        io.printStackTrace()
        null
    }
}