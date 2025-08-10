package com.fahimdev.composeboilerplate.di

import com.fahimdev.domain.repository.MovieRepository
import com.fahimdev.domain.usecase.GetMovieByIdUseCase
import com.fahimdev.domain.usecase.GetMovieListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun provideGetCharacterListUseCase(movieRepository: MovieRepository): GetMovieListUseCase {
        return GetMovieListUseCase(movieRepository)
    }

    @Provides
    fun provideGetCharacterByIdUseCase(movieRepository: MovieRepository): GetMovieByIdUseCase {
        return GetMovieByIdUseCase(movieRepository)
    }
}