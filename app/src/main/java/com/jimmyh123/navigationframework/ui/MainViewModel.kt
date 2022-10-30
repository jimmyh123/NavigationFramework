package com.jimmyh123.navigationframework.ui

import androidx.lifecycle.ViewModel
import com.jimmyh123.navigationframework.data.AppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState(testItem = 0))
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

}

