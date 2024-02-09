package com.gemini.assistant.utils.helpers

import com.gemini.assistant.utils.Constants._5000L
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

fun <T> Flow<T>.toHotFlow(
    coroutineScope: CoroutineScope,
    unsubscribeTimeout: Long = _5000L,
    sharingStrategy: SharingStarted = SharingStarted.WhileSubscribed(unsubscribeTimeout),
    initialValue: T
) = stateIn(coroutineScope, sharingStrategy, initialValue)