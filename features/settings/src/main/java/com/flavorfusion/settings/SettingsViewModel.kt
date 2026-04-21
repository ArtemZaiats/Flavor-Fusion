package com.flavorfusion.settings

import androidx.lifecycle.viewModelScope
import com.flavorfusion.common_domain.interactors.AuthInteractor
import com.flavorfusion.common_domain.interactors.SettingsInteractor
import com.flavorfusion.common_ui.Executor
import com.flavorfusion.common_ui.model.profile.toUi
import com.flavorfusion.core_ui.mvi.MviViewModel
import com.flavorfusion.settings.model.CategoryItem
import com.flavorfusion.settings.providers.LogOutDataProvider
import com.flavorfusion.settings.providers.SettingsCategoryProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsInteractor: SettingsInteractor,
    private val authInteractor: AuthInteractor,
    private val settingsCategoryProvider: SettingsCategoryProvider,
    private val logOutDataProvider: LogOutDataProvider,
    config: SettingsContract.Config,
    executor: Executor
) : MviViewModel<SettingsContract.State, SettingsContract.Event>(config), Executor by executor {

    override fun handleEvent(event: SettingsContract.Event) {
        when (event) {
            is SettingsContract.Event.OnItemClicked -> handleItemClicked(event.id)
            SettingsContract.Event.OnLogOutClicked -> onLogoutClicked()
            SettingsContract.Event.OnLogOutConfirmed -> onLogout()
        }
    }

    init {
        updateCategories()
        observeAlcoholic()
        loadUserProfile()
    }

    private fun handleItemClicked(id: Int) {
        when (id) {
            CategoryItem.APP_THEME.id -> publish { SettingsContract.Effect.NavigateToAppTheme }
            CategoryItem.SHOW_ALCOHOLIC.id -> onAlcoholicSwitchChanged()
            else -> {}
        }
    }

    private fun observeAlcoholic() {
        settingsInteractor.getShowAlcoholicFlow().onEach { show ->
            updateAlcoholicSwitch(show)
        }.launchIn(viewModelScope)
    }

    private fun onAlcoholicSwitchChanged() {
        viewModelScope.launch {
            val showAlcoholic = settingsInteractor.getShowAlcoholicFlow().first()
            settingsInteractor.setShowAlcoholic(showAlcoholic.not())
        }
    }

    private fun updateAlcoholicSwitch(enabled: Boolean) {
        val categories = state.value.categories.map { category ->
            val updatedItems = category.items.map { item ->
                if (item.id == CategoryItem.SHOW_ALCOHOLIC.id) {
                    item.copy(isSwitchActive = enabled)
                } else {
                    item
                }
            }
            category.copy(items = updatedItems)
        }
        dispatch(SettingsContract.Action.UpdateCategories(categories))
    }

    private fun updateCategories() {
        val categories = settingsCategoryProvider.provideData()
        dispatch(SettingsContract.Action.UpdateCategories(categories))
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            val profile = authInteractor.getUserProfile().toUi()
            dispatch(SettingsContract.Action.UpdateProfile(profile))
        }
    }

    private fun onLogoutClicked() {
        val dialogData = logOutDataProvider.provideData()
        dispatch(SettingsContract.Action.UpdateDialogData(dialogData))
        publish { SettingsContract.Effect.ShowLogOutDialog }
    }

    private fun onLogout() {
        viewModelScope.launch {
            authInteractor.logout()
        }
    }
}