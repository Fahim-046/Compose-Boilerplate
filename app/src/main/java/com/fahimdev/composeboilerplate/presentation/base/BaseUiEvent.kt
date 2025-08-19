package com.fahimdev.composeboilerplate.presentation.base

sealed class BaseUiEvent {
    data object PopBackStack : BaseUiEvent()
    data class Navigate(val route: String) : BaseUiEvent()
    data class ShowToast(val message: String) : BaseUiEvent()
    data class ShowSnackBar(
        val message: String,
        val action: String? = null
    ) : BaseUiEvent()
}