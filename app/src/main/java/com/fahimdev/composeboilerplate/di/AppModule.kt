package com.fahimdev.composeboilerplate.di

import com.fahimdev.core.network.ApiClient
import com.fahimdev.data.datasource.remote.MovieApiService
import com.fahimdev.data.repository.MovieRepositoryImpl
import com.fahimdev.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return ApiClient.retrofitInstance(gsonConverterFactory)
    }

    @Provides
    fun provideCharacterApiService(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }

    @Provides
    fun provideCharacterRepository(characterApiService: MovieApiService): MovieRepository {
        return MovieRepositoryImpl(characterApiService)
    }
}