package com.fahimdev.composeboilerplate

import androidx.lifecycle.viewModelScope
import com.fahimdev.composeboilerplate.presentation.base.BaseViewModel
import com.fahimdev.core.manager.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class MainActivityViewModel(
    private val dataStoreManager: DataStoreManager,
) : BaseViewModel() {
    private val _isUserLoggedIn = MutableStateFlow<Boolean?>(null)
    val isUserLoggedIn = _isUserLoggedIn.asStateFlow()

    private val _isDarkModeEnabled = MutableStateFlow<Boolean?>(null)
    val isDarkModeEnabled = _isDarkModeEnabled.asStateFlow()

    init {
        loadUserPreferences()
    }

    fun onThemeChanged(enabled: Boolean) = viewModelScope.launch {
        _isDarkModeEnabled.value = enabled
        dataStoreManager.saveBoolean("dark_mode", enabled)
    }

    private fun loadUserPreferences() = viewModelScope.launch {
        _isUserLoggedIn.value = dataStoreManager.getBoolean("is_logged_in") ?: false
        _isDarkModeEnabled.value = dataStoreManager.getBoolean("dark_mode") ?: false
    }
}
