package com.fahimdev.shared.presentation.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

open class BaseViewModel {
    protected val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private var _state = MutableStateFlow(BaseUiState())
    val state get() = _state

    private val _baseUiEvent = Channel<BaseUiEvent>()
    val baseUiEvent = _baseUiEvent.receiveAsFlow()

    fun onBaseEvent(event: BaseEvent) {
        when (event) {
            BaseEvent.OnShowLoadingDialog -> {
                _state.value = _state.value.copy(
                    isLoading = true
                )
            }
            BaseEvent.OnDismissLoadingDialog -> {
                _state.value = _state.value.copy(
                    isLoading = false
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

    open fun onDestroy() {
        viewModelScope.cancel()
    }
}