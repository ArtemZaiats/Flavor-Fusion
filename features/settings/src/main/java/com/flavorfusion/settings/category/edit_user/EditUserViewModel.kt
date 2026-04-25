package com.flavorfusion.settings.category.edit_user

import android.content.Context
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.flavorfusion.common_domain.interactors.AuthInteractor
import com.flavorfusion.common_domain.model.Result
import com.flavorfusion.common_domain.model.UserProfile
import com.flavorfusion.common_ui.Executor
import com.flavorfusion.core_ui.mvi.CommonEffect
import com.flavorfusion.core_ui.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import androidx.core.net.toUri

@HiltViewModel
class EditUserViewModel @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val authInteractor: AuthInteractor,
    config: EditUserContract.Config,
    executor: Executor
) : MviViewModel<EditUserContract.State, EditUserContract.Event>(config), Executor by executor {

    init {
        loadProfile()
    }

    override fun handleEvent(event: EditUserContract.Event) {
        when (event) {
            is EditUserContract.Event.OnFirstNameChanged ->
                dispatch(EditUserContract.Action.UpdateFirstName(event.value))
            is EditUserContract.Event.OnLastNameChanged ->
                dispatch(EditUserContract.Action.UpdateLastName(event.value))
            is EditUserContract.Event.OnImagePicked ->
                dispatch(EditUserContract.Action.UpdatePickedImage(event.uri))
            EditUserContract.Event.OnSaveClicked -> onSave()
            EditUserContract.Event.OnBackClicked ->
                publish { EditUserContract.Effect.NavigateBack }
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            val profile = authInteractor.getUserProfileFlow().first() ?: return@launch
            dispatch(
                EditUserContract.Action.SeedProfile(
                    email = profile.email,
                    firstName = profile.firstName,
                    lastName = profile.lastName,
                    avatarUrl = profile.avatarUrl
                )
            )
        }
    }

    private fun onSave() {
        if (!currentState.hasChanges || currentState.isSaving) return

        dispatch(EditUserContract.Action.ShowSaving(true))

        viewModelScope.launch(Dispatchers.IO) {
            val state = currentState

            val resolvedAvatarUrl = state.pickedImageUri?.let { uri ->
                when (val upload = uploadPickedImage(uri)) {
                    is Result.Success -> upload.data
                    is Result.Error -> {
                        dispatch(EditUserContract.Action.ShowSaving(false))
                        publish { CommonEffect.Toast(upload.error.toString()) }
                        return@launch
                    }
                }
            } ?: state.initialAvatarUrl

            val payload = UserProfile(
                id = "",
                email = state.email,
                firstName = if (state.firstName != state.initialFirstName) state.firstName else "",
                lastName = if (state.lastName != state.initialLastName) state.lastName else "",
                avatarUrl = if (resolvedAvatarUrl != state.initialAvatarUrl) resolvedAvatarUrl else ""
            )

            when (val result = authInteractor.updateUserProfile(payload)) {
                is Result.Success -> {
                    dispatch(EditUserContract.Action.ShowSaving(false))
                    publish { EditUserContract.Effect.NavigateBack }
                }
                is Result.Error -> {
                    dispatch(EditUserContract.Action.ShowSaving(false))
                    publish { CommonEffect.Toast(result.error.toString()) }
                }
            }
        }
    }

    private suspend fun uploadPickedImage(uriString: String): Result<String> {
        val uri = uriString.toUri()
        val resolver = context.contentResolver
        val mimeType = resolver.getType(uri) ?: DEFAULT_MIME_TYPE
        val bytes = withContext(Dispatchers.IO) {
            resolver.openInputStream(uri)?.use { it.readBytes() }
        } ?: return Result.Error(
            com.flavorfusion.common_domain.model.error.DataError.Network.CustomServerError(
                "Could not read image"
            )
        )
        return authInteractor.uploadAvatar(bytes, mimeType)
    }

    private companion object {
        const val DEFAULT_MIME_TYPE = "image/jpeg"
    }
}
