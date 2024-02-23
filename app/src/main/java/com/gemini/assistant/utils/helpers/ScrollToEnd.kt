package com.gemini.assistant.utils.helpers

import androidx.compose.foundation.lazy.LazyListState

suspend fun LazyListState.scrollToEnd(scrollOffset: Int) {
    scrollToItem(
        index = layoutInfo.totalItemsCount - 1,
        scrollOffset = scrollOffset
    )
}

suspend fun LazyListState.animateScrollToEnd(scrollOffset: Int) {
    animateScrollToItem(
        index = layoutInfo.totalItemsCount - 1,
        scrollOffset = scrollOffset
    )
}