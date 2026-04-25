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
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
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

    override suspend fun updateUserProfile(profile: UserProfile): Result<Unit> {
        return try {
            supabaseClient.auth.updateUser {
                data = buildJsonObject {
                    if (profile.firstName.isNotBlank()) put("given_name", profile.firstName)
                    if (profile.lastName.isNotBlank()) put("family_name", profile.lastName)
                    if (profile.avatarUrl.isNotBlank()) put("avatar_url", profile.avatarUrl)
                }
            }
            cacheCurrentUserProfile()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(
                DataError.Network.CustomServerError(
                    e.message ?: "Update user profile failed"
                )
            )
        }
    }

    override suspend fun uploadAvatar(bytes: ByteArray, mimeType: String): Result<String> {
        return try {
            val userId = supabaseClient.auth.currentUserOrNull()?.id
                ?: return Result.Error(
                    DataError.Network.CustomServerError("User not authenticated")
                )

            val extension = when (mimeType.lowercase()) {
                "image/png" -> "png"
                "image/webp" -> "webp"
                "image/gif" -> "gif"
                else -> "jpg"
            }
            val path = "$userId.$extension"
            val bucket = supabaseClient.storage.from(AVATARS_BUCKET)
            bucket.upload(path, bytes) { upsert = true }
            val publicUrl = bucket.publicUrl(path)
            Result.Success("$publicUrl?t=${System.currentTimeMillis()}")
        } catch (e: Exception) {
            println("Avatar upload failed: ${e.message}")
            Result.Error(
                DataError.Network.CustomServerError(e.message ?: "Avatar upload failed")
            )
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
                    ?.substringBefore(" ")
                ?: "",
            lastName = meta?.getStringByKey("family_name")
                ?: meta
                    ?.getStringByKey("full_name")
                    ?.substringAfter(" ", "")
                ?: "",
            avatarUrl = meta?.getStringByKey("avatar_url") ?: ""
        )
    }

    private companion object {
        const val AVATARS_BUCKET = "avatars"
    }
}
