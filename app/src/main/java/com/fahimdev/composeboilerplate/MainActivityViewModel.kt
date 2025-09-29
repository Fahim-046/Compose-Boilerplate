package com.fahimdev.composeboilerplate

import androidx.lifecycle.viewModelScope
import com.fahimdev.composeboilerplate.presentation.base.BaseViewModel
import com.fahimdev.core.manager.DataStoreManager
import com.fahimdev.domain.usecase.GetTrendingMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class MainActivityViewModel(
    private val dataStoreManager: DataStoreManager,
) : BaseViewModel() {

    private val _isDarkModeEnabled = MutableStateFlow(
        runBlocking { dataStoreManager.getBoolean("dark_mode") ?: false }
    )
    val isDarkModeEnabled get() = _isDarkModeEnabled

    fun onThemeChanged(enabled: Boolean) = viewModelScope.launch {
        _isDarkModeEnabled.value = enabled
        dataStoreManager.saveBoolean("dark_mode", enabled)
    }
}