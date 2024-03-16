package com.gemini.assistant.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gemini.assistant.domain.usecases.AppUseCases
import com.gemini.assistant.utils.helpers.toHotFlow
import com.gemini.assistant.utils.internet_connection.ConnectivityStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InternetConnectionViewModel @Inject constructor(private val appUseCases: AppUseCases): ViewModel() {

    val internetConnection = appUseCases.connectionHandlerUseCase().toHotFlow(coroutineScope = viewModelScope, initialValue = ConnectivityStatus.Unavailable)
}