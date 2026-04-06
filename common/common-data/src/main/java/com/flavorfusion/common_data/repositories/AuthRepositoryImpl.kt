package com.flavorfusion.common_data.repositories

import com.flavorfusion.common_domain.model.Result
import com.flavorfusion.common_domain.model.error.DataError
import com.flavorfusion.common_domain.repositories.AuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            supabaseClient.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Network.CustomServerError(e.message ?: "Login failed"))
        }
    }

    override suspend fun signUp(email: String, password: String): Result<Unit> {
        return try {
            supabaseClient.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Network.CustomServerError(e.message ?: "Sign up failed"))
        }
    }

    override suspend fun logout() {
        runCatching { supabaseClient.auth.signOut() }
    }

    override fun getAuthStateFlow(): Flow<Boolean> {
        return supabaseClient.auth.sessionStatus.map { status ->
            status is SessionStatus.Authenticated
        }
    }
}
