package com.fahimdev.shared.presentation.base

data class BaseUiState(
    val isLoading: Boolean = false,
    val isErrorDialogVisible: Boolean = false,
    val message: Event<String>? = null,
)