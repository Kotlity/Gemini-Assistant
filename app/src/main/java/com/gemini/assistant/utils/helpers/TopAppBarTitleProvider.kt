package com.gemini.assistant.utils.helpers

import android.content.Context
import com.gemini.assistant.R

interface TopAppBarTitleProvider {

    fun topAppBarTitle(context: Context): String
}

class GeminiChatTopAppBarTitle: TopAppBarTitleProvider {

    override fun topAppBarTitle(context: Context): String {
        return context.getString(R.string.gemini_chat_top_app_bar_title)
    }
}

class GeminiMessageTopAppBarTitle: TopAppBarTitleProvider {

    override fun topAppBarTitle(context: Context): String {
        return context.getString(R.string.gemini_message_top_app_bar_title)
    }
}