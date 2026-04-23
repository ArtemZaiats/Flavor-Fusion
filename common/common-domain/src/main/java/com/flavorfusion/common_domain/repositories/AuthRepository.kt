package com.flavorfusion.common_domain.repositories

import com.flavorfusion.common_domain.model.AuthState
import com.flavorfusion.common_domain.model.Result
import com.flavorfusion.common_domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun signUp(email: String, password: String): Result<Unit>
    suspend fun signInWithGoogle(idToken: String, rawNonce: String): Result<Unit>
    suspend fun logout()
    fun getUserProfileFlow(): Flow<UserProfile?>
    fun getAuthStateFlow(): Flow<AuthState>
}
