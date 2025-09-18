package com.fahimdev.domain.repository

import android.content.Intent
import com.fahimdev.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun getCurrentUser(): User?

    suspend fun signInWithGoogle(): Result<User?>

    suspend fun signOut()

    suspend fun isUserSignedIn(): Boolean
}