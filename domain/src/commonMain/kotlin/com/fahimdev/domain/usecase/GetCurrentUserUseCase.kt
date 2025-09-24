package com.fahimdev.domain.usecase

import com.fahimdev.domain.repository.AuthRepository

class GetCurrentUserUseCase(private val authRepository: AuthRepository) {
    suspend fun invoke() = authRepository.getCurrentUser()
}