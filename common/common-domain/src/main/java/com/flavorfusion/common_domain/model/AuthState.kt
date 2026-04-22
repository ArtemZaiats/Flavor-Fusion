package com.flavorfusion.common_domain.model

sealed interface AuthState {
    data object Loading : AuthState
    data object Authenticated : AuthState
    data object NotAuthenticated : AuthState
}