package com.fahimdev.shared.presentation.settings

import com.fahimdev.shared.presentation.base.BaseViewModel
import com.fahimdev.shared.presentation.settings.components.AppearanceTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel : BaseViewModel() {
    private val _isDarkModeEnabled = MutableStateFlow(AppearanceTheme.LIGHT)
    val isDarkModeEnabled = _isDarkModeEnabled

    init {
        isDarkModeEnable()
    }

    fun onThemeChanged(enabled: Boolean) = viewModelScope.launch {
        _isDarkModeEnabled.value = if (enabled) AppearanceTheme.DARK else AppearanceTheme.LIGHT
    }

    private fun isDarkModeEnable() = viewModelScope.launch {
        _isDarkModeEnabled.value = AppearanceTheme.LIGHT
    }
}