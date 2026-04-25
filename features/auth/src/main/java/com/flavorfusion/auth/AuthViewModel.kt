package com.flavorfusion.auth

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.flavorfusion.common_domain.interactors.AuthInteractor
import com.flavorfusion.common_domain.model.Result
import com.flavorfusion.common_ui.Executor
import com.flavorfusion.core_ui.mvi.CommonEffect
import com.flavorfusion.core_ui.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
    private val googleSignInHelper: GoogleSignInHelper,
    config: AuthContract.Config,
    executor: Executor
) : MviViewModel<AuthContract.State, AuthContract.Event>(config), Executor by executor {

    override fun handleEvent(event: AuthContract.Event) {
        when (event) {
            is AuthContract.Event.OnEmailChanged -> dispatch(AuthContract.Action.UpdateEmail(event.value))
            is AuthContract.Event.OnPasswordChanged -> dispatch(AuthContract.Action.UpdatePassword(event.value))
            is AuthContract.Event.OnConfirmPasswordChanged -> dispatch(AuthContract.Action.UpdateConfirmPassword(event.value))
            is AuthContract.Event.OnTabChanged -> dispatch(AuthContract.Action.UpdateTab(event.isLogin))
            AuthContract.Event.OnSubmitClicked -> onSubmit()
            is AuthContract.Event.OnGoogleSignInClicked -> onGoogleSignIn(event.activityContext)
        }
    }

    private fun onSubmit() {
        if (!validate()) return

        dispatch(AuthContract.Action.ShowLoading(true))

        viewModelScope.launch(Dispatchers.IO) {
            val result = if (currentState.isLogin) {
                authInteractor.login(currentState.email, currentState.password)
            } else {
                authInteractor.signUp(currentState.email, currentState.password)
            }

            dispatch(AuthContract.Action.ShowLoading(false))

            when (result) {
                is Result.Success -> publish { AuthContract.Effect.NavigateToMain }
                is Result.Error -> publish { CommonEffect.Toast(result.error.toString()) }
            }
        }
    }

    private fun onGoogleSignIn(activityContext: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            dispatch(AuthContract.Action.ShowLoading(true))
            when (val signIn = googleSignInHelper.requestSignIn(activityContext)) {
                is GoogleSignInResult.Success -> {
                    val result = authInteractor.signInWithGoogle(signIn.idToken, signIn.rawNonce)
                    dispatch(AuthContract.Action.ShowLoading(false))
                    when (result) {
                        is Result.Success -> publish { AuthContract.Effect.NavigateToMain }
                        is Result.Error -> publish { CommonEffect.Toast(result.error.toString()) }
                    }
                }
                is GoogleSignInResult.Failure -> {
                    dispatch(AuthContract.Action.ShowLoading(false))
                    println(signIn.message)
                    publish { CommonEffect.Toast(signIn.message) }
                }
            }
        }
    }

    private fun validate(): Boolean {
        var valid = true

        if (currentState.email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(currentState.email).matches()) {
            dispatch(AuthContract.Action.SetEmailError("Please enter a valid email"))
            valid = false
        }

        if (currentState.password.length < 6) {
            dispatch(AuthContract.Action.SetPasswordError("Password must be at least 6 characters"))
            valid = false
        }

        if (!currentState.isLogin && currentState.password != currentState.confirmPassword) {
            dispatch(AuthContract.Action.SetPasswordError("Passwords do not match"))
            valid = false
        }

        return valid
    }
}
