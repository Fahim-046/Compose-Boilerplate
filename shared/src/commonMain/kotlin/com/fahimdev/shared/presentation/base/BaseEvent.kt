package com.fahimdev.shared.presentation.base

sealed class BaseEvent {
    data object OnShowLoadingDialog : BaseEvent()
    data object OnDismissLoadingDialog : BaseEvent()
    data object OnShowErrorDialog : BaseEvent()
    data object OnDismissErrorDialog : BaseEvent()
}