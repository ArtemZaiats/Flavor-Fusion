package com.flavorfusion.settings

import com.flavorfusion.common_ui.Executor
import com.flavorfusion.core_ui.mvi.MviViewModel
import com.flavorfusion.settings.model.CategoryItem
import com.flavorfusion.settings.providers.SettingsCategoryProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val executor: Executor,
    config: SettingsContract.Config
) : MviViewModel<SettingsContract.State, SettingsContract.Event>(config) {

    override fun handleEvent(event: SettingsContract.Event) {
        when (event) {
            SettingsContract.Event.OnLogOutClicked -> {}
            is SettingsContract.Event.OnItemClicked -> handleItemClicked(event.id)
        }
    }

    init {
        updateCategories()
    }

    private fun handleItemClicked(id: Int) {
        when (id) {
            CategoryItem.APP_THEME.id -> publish { SettingsContract.Effect.NavigateToAppTheme }
            CategoryItem.SHOW_ALCOHOLIC.id -> onAlcoholicSwitchChanged()
            else -> {}
        }
    }

    private fun onAlcoholicSwitchChanged() {

    }

    private fun updateCategories() {
        val categories = SettingsCategoryProvider().provideData()
        dispatch(SettingsContract.Action.UpdateCategories(categories))
    }
}