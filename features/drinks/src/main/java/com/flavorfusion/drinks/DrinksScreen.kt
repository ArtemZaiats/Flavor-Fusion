package com.flavorfusion.drinks

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flavorfusion.common_ui.R
import com.flavorfusion.common_ui.compose.EffectHandler
import com.flavorfusion.common_ui.model.drink.DrinkUi
import com.flavorfusion.core_ui.compose.OnLifecycleEvent
import com.flavorfusion.drinks.compose.DrinksGrid
import com.flavorfusion.drinks.compose.DrinksList
import com.flavorfusion.drinks.model.DrinksDataPreviewProvider

@Composable
fun DrinksScreen(
    navigateToDrinkDetails: (String) -> Unit
) {
    val viewModel: DrinksViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    OnLifecycleEvent(
        onCreate = { viewModel.setEvent(DrinksContract.Event.OnRefresh) }
    )

    EffectHandler(viewModel = viewModel) {
        when (it) {
            is DrinksContract.Effect.NavigateToDrinkDetails -> navigateToDrinkDetails(it.drinkId)
        }
    }

    DrinksScreen(
        state = state,
        onEvent = viewModel::handleEvent
    )
}

@Composable
fun DrinksScreen(
    state: DrinksContract.State,
    onEvent: (DrinksContract.Event) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        SearchPanel(onSearchClick = { onEvent.invoke(DrinksContract.Event.OnSearchClicked) })
        DrinksGrid(
            drinks = state.drinks,
            onDrinkClick = { onEvent.invoke(DrinksContract.Event.OnDrinkClicked(it)) }
        )
    }
}

@Composable
fun SearchPanel(
    modifier: Modifier = Modifier,
    onSearchClick: (String) -> Unit,
) {
    val (text, setText) = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = text,
            onValueChange = setText,
            placeholder = {
                Text(text = stringResource(R.string.feature_drinks_search_drinks))
            },
            shape = RoundedCornerShape(16.dp),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 20.sp
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.feature_drinks_search_drinks),
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black
                )
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (text.isNotEmpty()) {
                        focusManager.clearFocus()
                        onSearchClick(text)
                        setText("")
                    }
                }
            ),
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .height(54.dp)
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(16.dp),
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF35F8E6),
                            Color(0xFF57F55E),
                            Color.Blue
                        )
                    )
                )
        )

        TextButton(
            onClick = {
                if (text.isNotEmpty()) {
                    focusManager.clearFocus()
                    onSearchClick(text)
                    setText("")
                } else {
                    focusManager.clearFocus()
                }
            },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = stringResource(R.string.feature_drinks_search))
        }
    }
}

@Preview
@Composable
fun DrinksScreenPreview(
    @PreviewParameter(DrinksDataPreviewProvider::class) drinks: List<DrinkUi>
) {
    val state = DrinksContract.State(
        drinks = drinks,
    )
    DrinksScreen(state = state, onEvent = {})
}