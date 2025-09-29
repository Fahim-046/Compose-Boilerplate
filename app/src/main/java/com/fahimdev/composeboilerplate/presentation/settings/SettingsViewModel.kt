package com.fahimdev.composeboilerplate.presentation.settings

import androidx.lifecycle.viewModelScope
import com.fahimdev.composeboilerplate.presentation.base.BaseViewModel
import com.fahimdev.composeboilerplate.presentation.settings.components.AppearanceTheme
import com.fahimdev.core.manager.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel(private val dataStoreManager: DataStoreManager): BaseViewModel(){
    private val _isDarkModeEnabled = MutableStateFlow(AppearanceTheme.LIGHT)
    val isDarkModeEnabled = _isDarkModeEnabled
    init {
        isDarkModeEnable()
    }
    fun onThemeChanged(enabled: Boolean) = viewModelScope.launch{
        dataStoreManager.saveBoolean("dark_mode", enabled)
        _isDarkModeEnabled.value = if(enabled) AppearanceTheme.DARK else AppearanceTheme.LIGHT
    }

    private fun isDarkModeEnable() = viewModelScope.launch {
        val isDarkMode = dataStoreManager.getBoolean("dark_mode") ?: false
        if(isDarkMode){
            _isDarkModeEnabled.value = AppearanceTheme.DARK
        }else{
            _isDarkModeEnabled.value = AppearanceTheme.LIGHT
        }
    }
}