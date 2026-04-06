package com.flavorfusion.common_domain.repositories

import com.flavorfusion.common_domain.model.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun signUp(email: String, password: String): Result<Unit>
    suspend fun logout()
    fun getAuthStateFlow(): Flow<Boolean>
}
