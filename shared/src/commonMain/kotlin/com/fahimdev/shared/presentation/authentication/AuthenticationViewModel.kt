package com.fahimdev.shared.presentation.authentication

import com.fahimdev.core.Event
import com.fahimdev.domain.repository.AuthRepository
import com.fahimdev.shared.presentation.authentication.events.AuthenticationEvents
import com.fahimdev.shared.presentation.authentication.states.AuthenticationStates
import com.fahimdev.shared.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthenticationViewModel(
    private val authRepository: AuthRepository
) : BaseViewModel() {
    val states = MutableStateFlow(AuthenticationStates())

    fun onEvent(event: AuthenticationEvents) {
        when (event) {
            is AuthenticationEvents.SignInWithGoogle -> signInWithGoogle()
            is AuthenticationEvents.OnEmailChange -> {
                states.value = states.value.copy(email = event.email)
            }

            is AuthenticationEvents.OnPasswordChange -> {
                states.value = states.value.copy(password = event.password)
            }

            is AuthenticationEvents.OnPasswordVisibilityChange -> {
                states.value = states.value.copy(isPasswordVisible = event.isPasswordVisible)
            }

            is AuthenticationEvents.SignInWithFacebook -> TODO()
            AuthenticationEvents.SignIn -> TODO()
            AuthenticationEvents.SignUp -> TODO()
        }
    }

    private fun signInWithGoogle() = viewModelScope.launch {
        val result = authRepository.signInWithGoogle()

        result.fold(onSuccess = { user ->
            if (user != null) {
                states.value = states.value.copy(message = Event("User signed in successfully"))
            } else {
                states.value = states.value.copy(message = Event("Sign in failed"))
            }
        }, onFailure = {
            states.value = states.value.copy(message = Event(it.message))
        })
    }
}