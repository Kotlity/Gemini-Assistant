package com.gemini.assistant.presentation.states

import com.gemini.assistant.domain.model.SearchRequestModel
import com.gemini.assistant.domain.model.SearchResponseModel
import com.gemini.assistant.presentation.states.modal_bottom_sheet_state.ModalBottomSheetState
import com.gemini.assistant.utils.GeminiResult

data class GeminiSearchState(
    val searchRequestModel: SearchRequestModel = SearchRequestModel(),
    val searchResponseModel: GeminiResult<SearchResponseModel> = GeminiResult.Undefined(),
    val modalBottomSheetState: ModalBottomSheetState = ModalBottomSheetState(),
    val visiblePermissionDialogQueue: List<String> = emptyList()
)