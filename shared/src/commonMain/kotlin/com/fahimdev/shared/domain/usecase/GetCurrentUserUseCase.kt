package com.fahimdev.shared.domain.usecase

import com.fahimdev.shared.domain.repository.AuthRepository

class GetCurrentUserUseCase(private val authRepository: AuthRepository) {
    suspend fun invoke() = authRepository.getCurrentUser()
}