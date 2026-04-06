package com.flavorfusion.common_domain.interactors

import com.flavorfusion.common_domain.repositories.AuthRepository
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun login(email: String, password: String) = authRepository.login(email, password)
    suspend fun signUp(email: String, password: String) = authRepository.signUp(email, password)
    suspend fun logout() = authRepository.logout()
    fun getAuthStateFlow() = authRepository.getAuthStateFlow()
}
