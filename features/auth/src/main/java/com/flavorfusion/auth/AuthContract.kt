package com.flavorfusion.auth

import com.flavorfusion.core_ui.mvi.MviConfig
import com.flavorfusion.core_ui.mvi.UiAction
import com.flavorfusion.core_ui.mvi.UiEffect
import com.flavorfusion.core_ui.mvi.UiEvent
import com.flavorfusion.core_ui.mvi.UiState
import javax.inject.Inject

interface AuthContract {

    class Config @Inject constructor() : MviConfig<State> {
        override fun initialState() = State()
        override fun reducer() = AuthReducer()
    }

    data class State(
        val isLogin: Boolean = true,
        val email: String = "",
        val password: String = "",
        val confirmPassword: String = "",
        val isLoading: Boolean = false,
        val emailError: String? = null,
        val passwordError: String? = null,
    ) : UiState

    sealed interface Event : UiEvent {
        data class OnEmailChanged(val value: String) : Event
        data class OnPasswordChanged(val value: String) : Event
        data class OnConfirmPasswordChanged(val value: String) : Event
        data class OnTabChanged(val isLogin: Boolean) : Event
        data object OnSubmitClicked : Event
        data class OnGoogleSignInClicked(val idToken: String, val rawNonce: String) : Event
    }

    sealed interface Action : UiAction {
        data class UpdateEmail(val email: String) : Action
        data class UpdatePassword(val password: String) : Action
        data class UpdateConfirmPassword(val confirmPassword: String) : Action
        data class UpdateTab(val isLogin: Boolean) : Action
        data class ShowLoading(val show: Boolean) : Action
        data class SetEmailError(val error: String?) : Action
        data class SetPasswordError(val error: String?) : Action
    }

    sealed interface Effect : UiEffect {
        data object NavigateToMain : Effect
        data class ShowError(val message: String) : Effect
    }
}
