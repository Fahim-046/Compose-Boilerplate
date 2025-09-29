package com.fahimdev.composeboilerplate.presentation.authentication

import androidx.lifecycle.viewModelScope
import com.fahimdev.composeboilerplate.presentation.authentication.events.AuthenticationEvents
import com.fahimdev.composeboilerplate.presentation.authentication.states.AuthenticationStates
import com.fahimdev.composeboilerplate.presentation.base.BaseViewModel
import com.fahimdev.core.models.Event
import com.fahimdev.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

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

        result.fold(onSuccess = {
            states.value = states.value.copy(message = Event("User signed in successfully"))
        }, onFailure = {
            states.value = states.value.copy(message = Event(it.message))
        })
    }
}