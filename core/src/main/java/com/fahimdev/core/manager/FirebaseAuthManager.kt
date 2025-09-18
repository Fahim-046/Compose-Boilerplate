package com.fahimdev.core.manager

import android.content.Context
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.NoCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

object FirebaseAuthManager {
    private lateinit var credentialManager: CredentialManager
    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    suspend fun googleSignIn(
        context: Context,
        apiKey: String,
        filterByAuthorizedAccounts: Boolean = true,
        doOnSuccess: (FirebaseUser?) -> Unit,
        doOnError: (String) -> Unit,
    ) {
        try {
            if (::credentialManager.isInitialized.not()) {
                credentialManager = CredentialManager.create(context)
            }
            val result = try {
                requestSignIn(context, apiKey, true)
            } catch (e: NoCredentialException) {
                try {
                    requestSignIn(context, apiKey, false)
                } catch (e2: NoCredentialException) {
                    doOnError("No Google accounts found on this device. Please add a Google account in Settings to use Google Sign-In.")
                    return
                }
            }

            result?.let {
                doOnSuccess(it)
            } ?: run {
                doOnError("Sign-in failed. Please try again.")
            }
        } catch (e: Exception) {
            val errorMessage = when {
                e.message?.contains("network") == true -> {
                    "Network error occurred. Please check your internet connection and try again."
                }
                else -> {
                    "Google Sign-In is currently unavailable. Please try again later or use alternative sign-in methods."
                }
            }
            doOnError(errorMessage)
        }
    }

    private suspend fun requestSignIn(
        context: Context,
        apiKey: String,
        filterByAuthorizedAccounts: Boolean
    ): FirebaseUser? {
        println("Attempting sign-in with filterByAuthorizedAccounts: $filterByAuthorizedAccounts")

        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption
            .Builder()
            .setFilterByAuthorizedAccounts(filterByAuthorizedAccounts)
            .setServerClientId(apiKey)
            .setAutoSelectEnabled(false)
            .build()

        val request: GetCredentialRequest = GetCredentialRequest
            .Builder()
            .addCredentialOption(googleIdOption)
            .build()

        val result: GetCredentialResponse = credentialManager.getCredential(
            request = request,
            context = context,
        )

        val userData = handleCredentials(result.credential)
        return userData?.let {
            val credential = GoogleAuthProvider.getCredential(it.idToken, null)
            firebaseAuth.signInWithCredential(credential).await()
            getCurrentUser()
        }
    }

    private fun handleCredentials(credential: Credential): GoogleIdTokenCredential? {
        when (credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        return GoogleIdTokenCredential.createFrom(credential.data)
                    } catch (e: GoogleIdTokenParsingException) {
                        println("Received an invalid google id token response $e")
                    }
                } else {
                    println("Unexpected type of credential")
                }
            }
            else -> println("Unexpected type of credential")
        }
        return null
    }

    fun isUserSignedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    fun getCurrentUser() = firebaseAuth.currentUser

    fun signOut() {
        firebaseAuth.signOut()
    }
}