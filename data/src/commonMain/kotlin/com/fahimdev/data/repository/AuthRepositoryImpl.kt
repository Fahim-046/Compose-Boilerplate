package com.fahimdev.data.repository

import android.content.Context
import com.fahimdev.core.manager.DataStoreManager
import com.fahimdev.core.manager.FirebaseAuthManager
import com.fahimdev.domain.entities.User
import com.fahimdev.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume

class AuthRepositoryImpl(
    private val dataStoreManager: DataStoreManager,
    private val firebaseAuthManager: FirebaseAuthManager,
    private val context: Context,
    private val googleApiKey: String
) : AuthRepository {

    override suspend fun getCurrentUser(): User? {
        val firebaseUser = firebaseAuthManager.getCurrentUser()
        return firebaseUser?.toUser()
    }

    override suspend fun signInWithGoogle(): Result<User?> = withContext(Dispatchers.IO) {
        suspendCancellableCoroutine { continuation ->
            CoroutineScope(Dispatchers.Main).launch {
                firebaseAuthManager.googleSignIn(
                    context = context,
                    apiKey = googleApiKey,
                    doOnSuccess = { firebaseUser ->
                        CoroutineScope(Dispatchers.IO).launch {
                            saveUser(firebaseUser)
                        }
                        val user = firebaseUser?.toUser()
                        continuation.resume(Result.success(user))
                    },
                    doOnError = { errorMessage ->
                        continuation.resume(Result.failure(Exception(errorMessage)))
                    }
                )
            }
        }
    }

    override suspend fun signOut() {
        firebaseAuthManager.signOut()
    }

    override suspend fun isUserSignedIn(): Boolean {
        return firebaseAuthManager.isUserSignedIn()
    }

    private suspend fun saveUser(user: FirebaseUser?) {
        if (user == null) return
        val firebaseUser = user.toUser()
        firebaseUser.let {
            dataStoreManager.saveString("user_id", it.id)
            dataStoreManager.saveString("user_email", it.email ?: "No valid email found")
            dataStoreManager.saveString("user_name", it.name ?: "No valid name found")
            dataStoreManager.saveString("user_image_url", it.imageUrl ?: "No profile image found")
        }
    }

    companion object {
        private fun FirebaseUser.toUser(): User {
            return User(
                id = this.uid,
                email = this.email,
                name = this.displayName,
                imageUrl = this.photoUrl?.toString()
            )
        }
    }
}