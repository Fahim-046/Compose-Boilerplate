package com.fahimdev.composeboilerplate.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

open class BaseViewModel: ViewModel() {
    private var _state = MutableStateFlow(BaseUiState())
    val state get() = _state

    private val _baseUiEvent = Channel<BaseUiEvent>()
    val baseUiEvent = _baseUiEvent.receiveAsFlow()

    fun onBaseEvent(event: BaseEvent){
        when(event){
            BaseEvent.OnShowLoadingDialog -> {
                _state.value = _state.value.copy(
                    isLoading = true
                )
            }
            BaseEvent.OnDismissLoadingDialog -> {
                _state.value = _state.value.copy(
                    isLoading = true
                )
            }
            BaseEvent.OnShowErrorDialog -> {
                _state.value = _state.value.copy(
                    isErrorDialogVisible = true
                )
            }
            BaseEvent.OnDismissErrorDialog -> {
                _state.value = _state.value.copy(
                    isErrorDialogVisible = false
                )
            }
        }
    }
}