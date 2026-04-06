package com.flavorfusion.auth

import com.flavorfusion.core_ui.mvi.Reducer
import com.flavorfusion.core_ui.mvi.UiAction

class AuthReducer : Reducer<AuthContract.State> {
    override fun AuthContract.State.reduce(action: UiAction): AuthContract.State {
        val action = action as? AuthContract.Action ?: return this

        return when (action) {
            is AuthContract.Action.UpdateEmail -> copy(email = action.email, emailError = null)
            is AuthContract.Action.UpdatePassword -> copy(password = action.password, passwordError = null)
            is AuthContract.Action.UpdateConfirmPassword -> copy(confirmPassword = action.confirmPassword)
            is AuthContract.Action.UpdateTab -> copy(
                isLogin = action.isLogin,
                email = "",
                password = "",
                confirmPassword = "",
                emailError = null,
                passwordError = null
            )
            is AuthContract.Action.ShowLoading -> copy(isLoading = action.show)
            is AuthContract.Action.SetEmailError -> copy(emailError = action.error)
            is AuthContract.Action.SetPasswordError -> copy(passwordError = action.error)
        }
    }
}
