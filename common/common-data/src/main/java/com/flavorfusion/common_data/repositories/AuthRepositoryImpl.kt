package com.flavorfusion.common_data.repositories

import com.flavorfusion.common_data.local_storage.data_store.DataStoreHelper
import com.flavorfusion.common_data.utils.Utils.getStringByKey
import com.flavorfusion.common_domain.model.AuthState
import com.flavorfusion.common_domain.model.Result
import com.flavorfusion.common_domain.model.UserProfile
import com.flavorfusion.common_domain.model.error.DataError
import com.flavorfusion.common_domain.repositories.AuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.providers.builtin.IDToken
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient,
    private val dataStoreHelper: DataStoreHelper
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            supabaseClient.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            cacheCurrentUserProfile()
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
            cacheCurrentUserProfile()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Network.CustomServerError(e.message ?: "Sign up failed"))
        }
    }

    override suspend fun logout() {
        runCatching {
            supabaseClient.auth.signOut()
            dataStoreHelper.clearAllData()
        }
    }

    override fun getAuthStateFlow(): Flow<AuthState> {
        return supabaseClient.auth.sessionStatus.map { status ->
            when (status) {
                is SessionStatus.Initializing -> AuthState.Loading
                is SessionStatus.Authenticated -> AuthState.Authenticated
                is SessionStatus.NotAuthenticated,
                is SessionStatus.RefreshFailure -> AuthState.NotAuthenticated
            }
        }
    }

    override suspend fun signInWithGoogle(idToken: String, rawNonce: String): Result<Unit> {
        return try {
            supabaseClient.auth.signInWith(IDToken) {
                this.idToken = idToken
                this.provider = Google
                this.nonce = rawNonce
            }
            cacheCurrentUserProfile()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Network.CustomServerError(e.message ?: "Google sign-in failed"))
        }
    }

    override fun getUserProfileFlow(): Flow<UserProfile?> = dataStoreHelper.userProfileFlow

    private suspend fun cacheCurrentUserProfile() {
        buildUserProfileFromSupabase()?.let { dataStoreHelper.saveUserProfile(it) }
    }

    private fun buildUserProfileFromSupabase(): UserProfile? {
        val user = supabaseClient.auth.currentUserOrNull() ?: return null
        val meta = user.userMetadata

        return UserProfile(
            id = user.id,
            email = user.email ?: "",
            firstName = meta?.getStringByKey("given_name")
                ?: meta
                    ?.getStringByKey("full_name")
                    ?.substringBefore(" ") ?: "",
            lastName = meta?.getStringByKey("family_name")
                ?: meta
                    ?.getStringByKey("full_name")
                    ?.substringAfter(" ", "")
                ?: "",
            avatarUrl = meta?.getStringByKey("avatar_url") ?: ""
        )
    }
}
