package com.fahimdev.shared.di

import com.fahimdev.shared.data.datasource.remote.apiClient.ApiClient
import com.fahimdev.shared.data.datasource.remote.apiService.MovieApiService
import com.fahimdev.shared.data.repository.AuthRepositoryImpl
import com.fahimdev.shared.data.repository.MovieRepositoryImpl
import com.fahimdev.shared.domain.usecases.GetMoviesUseCase
import com.fahimdev.shared.presentation.authentication.AuthenticationViewModel
import com.fahimdev.shared.presentation.movies.MoviesViewModel
import com.fahimdev.shared.presentation.settings.SettingsViewModel

class ViewModelFactory {
    companion object {
        fun createAuthenticationViewModel(): AuthenticationViewModel {
            val authRepository = AuthRepositoryImpl()
            return AuthenticationViewModel(authRepository)
        }

        fun createMoviesViewModel(): MoviesViewModel {
            val apiClient = ApiClient()
            val apiService = MovieApiService(apiClient)
            val repository = MovieRepositoryImpl(apiService)
            val useCase = GetMoviesUseCase(repository)
            return MoviesViewModel(useCase)
        }

        fun createSettingsViewModel(): SettingsViewModel {
            return SettingsViewModel()
        }
    }
}