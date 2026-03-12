package com.flavorfusion.flavorfusion.main

import androidx.lifecycle.viewModelScope
import com.flavorfusion.common_domain.interactors.SettingsInteractor
import com.flavorfusion.common_ui.error.ErrorMessageProvider
import com.flavorfusion.common_ui.model.toUi
import com.flavorfusion.core_ui.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingsInteractor: SettingsInteractor,
    private val errorMessageProvider: ErrorMessageProvider,
    config: MainContract.Config
) : MviViewModel<MainContract.State, MainContract.Event>(config) {

    override fun handleEvent(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.OnDialogActionClicked -> {}
        }
    }

    init {
        observeAppTheme()
        observeErrors()
    }

    private fun observeAppTheme() {
        settingsInteractor.getCurrentAppThemeFlow()
            .onEach { theme ->
                theme ?: return@onEach
                dispatch(MainContract.Action.UpdateAppTheme(theme.toUi()))
            }
            .launchIn(viewModelScope)
    }

    private fun observeErrors() {
        errorMessageProvider.errorFlow
            .onEach {
                dispatch(MainContract.Action.UpdateErrorMessage(it))
                publish { MainContract.Effect.ShowErrorDialog }
            }
            .launchIn(viewModelScope)
    }
}