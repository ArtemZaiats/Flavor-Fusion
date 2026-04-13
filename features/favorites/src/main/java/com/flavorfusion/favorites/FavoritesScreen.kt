package com.flavorfusion.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flavorfusion.favorites.compose.FavoritesList

@Composable
fun FavoritesScreen() {
    val viewModel: FavoritesViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    FavoritesScreen(state = state, onEvent = viewModel::setEvent)
}

@Composable
fun FavoritesScreen(
    state: FavoritesContract.State,
    onEvent: (FavoritesContract.Event) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        contentWindowInsets = WindowInsets(bottom = 0),
        topBar = {
            Text(
                text = "Favorites",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }
    ) { innerPadding ->
        when {
            state.loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    CircularProgressIndicator()
                }
            }

            state.isEmpty -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Text(text = "No favorites yet")
                }
            }

            else -> {
                FavoritesList(
                    favorites = state.favorites,
                    onRemoveClick = { onEvent(FavoritesContract.Event.OnFavoriteToggled(it)) },
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}
