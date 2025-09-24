package com.fahimdev.shared.data.repository

import com.fahimdev.shared.domain.entities.User
import com.fahimdev.shared.domain.repository.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    override suspend fun getCurrentUser(): User? {
        // TODO: Implement get current user
        return null
    }

    override suspend fun signInWithGoogle(): Result<User?> {
        // TODO: Implement actual Google Sign-In
        return Result.success(null)
    }

    override suspend fun signOut() {
        // TODO: Implement sign out
    }

    override suspend fun isUserSignedIn(): Boolean {
        // TODO: Implement check if user is signed in
        return false
    }
}