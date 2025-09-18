package com.fahimdev.composeboilerplate.presentation.base

import com.fahimdev.core.models.Event

data class BaseUiState(
    val isLoading: Boolean = false,
    val isErrorDialogVisible: Boolean = false,
    val message: Event<String>? = null,
)