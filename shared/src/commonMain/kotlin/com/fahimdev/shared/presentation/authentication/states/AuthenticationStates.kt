package com.fahimdev.shared.presentation.authentication.states

import com.fahimdev.core.Event

data class AuthenticationStates(
    var isLoading: Boolean = false,
    var message: Event<String?>? = null,
    var email: String = "",
    var password: String = "",
    var isPasswordVisible: Boolean = false
)