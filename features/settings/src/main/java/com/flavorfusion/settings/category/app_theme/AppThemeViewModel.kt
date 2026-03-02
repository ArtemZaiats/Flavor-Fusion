package com.flavorfusion.settings.category.app_theme

import androidx.lifecycle.viewModelScope
import com.flavorfusion.common_domain.interactors.SettingsInteractor
import com.flavorfusion.common_domain.model.app_theme.AppTheme
import com.flavorfusion.common_ui.Executor
import com.flavorfusion.common_ui.model.AppThemeUi
import com.flavorfusion.common_ui.model.toUi
import com.flavorfusion.core_ui.mvi.MviViewModel
import com.flavorfusion.settings.providers.AppThemesDataProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppThemeViewModel @Inject constructor(
    private val settingsInteractor: SettingsInteractor,
    private val executor: Executor,
    config: AppThemeContract.Config
) : MviViewModel<AppThemeContract.State, AppThemeContract.Event>(config), Executor by executor {
    override fun handleEvent(event: AppThemeContract.Event) {
        when (event) {
            is AppThemeContract.Event.OnBackClicked -> publish { AppThemeContract.Effect.NavigateBack }
            is AppThemeContract.Event.OnThemeSelected -> setTheme(event.theme.theme)
        }
    }

    init {
        observeThemes()
    }

    private fun observeThemes() {
        settingsInteractor.getCurrentAppThemeFlow().onEach { theme ->
            theme ?: return@onEach

            updateThemes(theme.toUi())
        }.launchIn(viewModelScope)
    }

    private fun updateThemes(theme: AppThemeUi) {
        val themes = AppThemesDataProvider().provideData()
            .map {
                it.copy(isSelected = it.theme.type == theme.theme.type)
            }
        dispatch(AppThemeContract.Action.UpdateThemes(themes = themes))
    }

    private fun setTheme(theme: AppTheme) {
        viewModelScope.launch {
            settingsInteractor.setCurrentAppTheme(theme)
        }
    }
}