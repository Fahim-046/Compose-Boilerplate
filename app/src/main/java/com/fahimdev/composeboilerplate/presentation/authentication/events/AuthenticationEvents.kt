package com.fahimdev.composeboilerplate.presentation.authentication.events

sealed class AuthenticationEvents{
    data object SignInWithGoogle : AuthenticationEvents()
}
