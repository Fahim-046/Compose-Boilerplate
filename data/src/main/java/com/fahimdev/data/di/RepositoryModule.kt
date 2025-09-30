package com.fahimdev.data.di

import com.fahimdev.core.manager.FirebaseAuthManager
import com.fahimdev.data.datasource.remote.apiClient.ApiClient
import com.fahimdev.data.datasource.remote.apiService.MovieApiService
import com.fahimdev.data.repository.AuthRepositoryImpl
import com.fahimdev.data.repository.MovieRepositoryImpl
import com.fahimdev.domain.repository.AuthRepository
import com.fahimdev.domain.repository.MovieRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        ApiClient()
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
            googleApiKey = "880395694721-r8gg987t3p1dbim2m8kr29mfracpojli.apps.googleusercontent.com"
        )
    }
}