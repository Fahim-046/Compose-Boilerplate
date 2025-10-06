package com.fahimdev.data.di

import com.fahimdev.core.manager.FirebaseAuthManager
import com.fahimdev.data.BuildConfig
import com.fahimdev.data.datasource.remote.apiClient.ApiClient
import com.fahimdev.data.datasource.remote.apiService.MovieApiService
import com.fahimdev.data.repository.AuthRepositoryImpl
import com.fahimdev.data.repository.MovieRepositoryImpl
import com.fahimdev.domain.repository.AuthRepository
import com.fahimdev.domain.repository.MovieRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        ApiClient(
            baseUrl = BuildConfig.BASE_URL,
            apiKey = BuildConfig.API_KEY
        )
    }

    single {
        MovieApiService(get())
    }

    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(
            dataStoreManager = get(),
            firebaseAuthManager = FirebaseAuthManager,
            context = get(),
            googleApiKey = BuildConfig.GOOGLE_WEB_CLIENT_ID
        )
    }
}