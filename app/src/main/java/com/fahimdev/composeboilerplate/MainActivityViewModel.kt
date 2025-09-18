package com.fahimdev.composeboilerplate

import androidx.lifecycle.viewModelScope
import com.fahimdev.composeboilerplate.presentation.base.BaseViewModel
import com.fahimdev.core.manager.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val dataStoreManager: DataStoreManager) :
    BaseViewModel() {
    private val _isDarkModeEnabled = MutableStateFlow(false)
    val isDarkModeEnabled get() = _isDarkModeEnabled

    init {
        onLoadTheme()
    }

    private fun onLoadTheme() = viewModelScope.launch {
        val isDarkMode = dataStoreManager.getBoolean("dark_mode") ?: false
        _isDarkModeEnabled.value = isDarkMode
    }

    fun onThemeChanged(enabled: Boolean) = viewModelScope.launch{
        _isDarkModeEnabled.value = enabled
    }
}