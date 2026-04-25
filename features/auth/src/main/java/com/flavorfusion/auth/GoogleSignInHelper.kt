package com.flavorfusion.auth

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.flavorfusion.feature_auth.BuildConfig
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import java.security.MessageDigest
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

sealed interface GoogleSignInResult {
    data class Success(val idToken: String, val rawNonce: String) : GoogleSignInResult
    data class Failure(val message: String) : GoogleSignInResult
}

@Singleton
class GoogleSignInHelper @Inject constructor() {

    suspend fun requestSignIn(activityContext: Context): GoogleSignInResult {
        val rawNonce = UUID.randomUUID().toString()
        val hashedNonce = MessageDigest.getInstance("SHA-256")
            .digest(rawNonce.toByteArray())
            .joinToString("") { "%02x".format(it) }

        val credentialManager = CredentialManager.create(activityContext)
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(BuildConfig.WEB_GOOGLE_CLIENT_ID)
            .setNonce(hashedNonce)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        return try {
            val result = credentialManager.getCredential(activityContext, request)
            val credential = result.credential as? GoogleIdTokenCredential
            if (credential != null) {
                GoogleSignInResult.Success(credential.idToken, rawNonce)
            } else {
                GoogleSignInResult.Failure("Unexpected credential type")
            }
        } catch (e: GetCredentialException) {
            GoogleSignInResult.Failure(e.message ?: "Google sign-in failed")
        }
    }
}
