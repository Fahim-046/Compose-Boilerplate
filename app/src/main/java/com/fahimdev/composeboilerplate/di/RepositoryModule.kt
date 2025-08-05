package com.fahimdev.composeboilerplate.di

import com.fahimdev.data.repository.CharacterRepositoryImpl
import com.fahimdev.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindCharacterRepository(impl: CharacterRepositoryImpl): CharacterRepository
}