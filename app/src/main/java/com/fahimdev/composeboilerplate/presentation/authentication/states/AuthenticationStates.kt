package com.fahimdev.composeboilerplate.presentation.authentication.states

import com.fahimdev.core.models.Event

data class AuthenticationStates(
    var isLoading: Boolean = false,
    var message: Event<String?>? = null
)