package com.flavorfusion.favorites

import androidx.lifecycle.viewModelScope
import com.flavorfusion.common_domain.interactors.FavoritesInteractor
import com.flavorfusion.common_domain.model.favorites.FavoriteItem
import com.flavorfusion.common_ui.model.favorites.FavoriteItemUi
import com.flavorfusion.common_ui.model.favorites.toUi
import com.flavorfusion.core_ui.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesInteractor: FavoritesInteractor,
    config: FavoritesContract.Config
) : MviViewModel<FavoritesContract.State, FavoritesContract.Event>(config) {

    init {
        observeFavorites()
    }

    override fun handleEvent(event: FavoritesContract.Event) {
        when (event) {
            is FavoritesContract.Event.OnFavoriteToggled -> removeFavorite(event.item)
        }
    }

    private fun observeFavorites() {
        favoritesInteractor
            .getFavoritesFlow()
            .onEach { items ->
                dispatch(FavoritesContract.Action.Loading(show = false))
                dispatch(FavoritesContract.Action.UpdateFavorites(favorites = items.toUi()))
            }
            .launchIn(viewModelScope)
    }

    private fun removeFavorite(item: FavoriteItemUi) {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesInteractor.toggleFavorite(
                FavoriteItem(
                    id = item.id,
                    name = item.name,
                    imageUrl = item.imageUrl,
                    itemType = item.itemType
                )
            )
        }
    }
}
