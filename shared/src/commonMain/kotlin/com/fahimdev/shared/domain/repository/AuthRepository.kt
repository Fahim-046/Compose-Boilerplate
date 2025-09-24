package com.fahimdev.shared.domain.repository

import com.fahimdev.shared.domain.entities.User

interface AuthRepository {
    suspend fun getCurrentUser(): User?
    suspend fun signInWithGoogle(): Result<User?>
    suspend fun signOut()
    suspend fun isUserSignedIn(): Boolean
}