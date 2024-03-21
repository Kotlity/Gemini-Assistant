package com.gemini.assistant.presentation.states.modal_bottom_sheet_state

import android.graphics.Bitmap

data class ModalBottomSheetState(
    val isModalBottomSheetExpanded: Boolean = false,
    val expandedImage: Bitmap? = null
)
