package com.fahimdev.data.di

import android.content.Context
import com.fahimdev.core.manager.DataStoreManager
import com.fahimdev.core.manager.FirebaseAuthManager
import com.fahimdev.data.datasource.remote.apiService.MovieApiService
import com.fahimdev.data.repository.AuthRepositoryImpl
import com.fahimdev.data.repository.MovieRepositoryImpl
import com.fahimdev.domain.repository.AuthRepository
import com.fahimdev.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        dataStoreManager: DataStoreManager,
        @ApplicationContext context: Context,
    ): AuthRepository {
        return AuthRepositoryImpl(
            dataStoreManager = dataStoreManager,
            firebaseAuthManager = FirebaseAuthManager,
            context = context,
            googleApiKey = "880395694721-r8gg987t3p1dbim2m8kr29mfracpojli.apps.googleusercontent.com"
        )
    }

    @Provides
    @Singleton
    fun provideMovieRepository(apiService: MovieApiService): MovieRepository{
        return MovieRepositoryImpl(apiService = apiService)
    }
}