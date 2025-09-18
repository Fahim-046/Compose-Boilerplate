package com.fahimdev.composeboilerplate.presentation.authentication.events

sealed class AuthenticationEvents{
    data object SignInWithGoogle : AuthenticationEvents()
    data class OnEmailChange(val email: String) : AuthenticationEvents()
    data class OnPasswordChange(val password: String) : AuthenticationEvents()
    data class OnPasswordVisibilityChange(val isPasswordVisible: Boolean) : AuthenticationEvents()
    data object SignUp : AuthenticationEvents()
    data object SignIn : AuthenticationEvents()
    data object SignInWithFacebook : AuthenticationEvents()
}
