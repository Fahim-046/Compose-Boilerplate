package com.fahimdev.data.di

import com.fahimdev.data.datasource.remote.apiClient.ApiClient
import com.fahimdev.data.datasource.remote.apiService.MovieApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {
    @Provides
    @Singleton
    fun provideApiClient(): ApiClient {
        return ApiClient()
    }

    @Provides
    @Singleton
    fun provideMovieApiService(apiClient: ApiClient): MovieApiService {
        return MovieApiService(apiClient = apiClient)
    }
}