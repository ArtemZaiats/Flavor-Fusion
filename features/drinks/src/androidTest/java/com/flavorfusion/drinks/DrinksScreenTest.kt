package com.flavorfusion.drinks

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.onNodeWithContentDescription
import com.flavorfusion.common_ui.model.drink.DrinkUi
import com.flavorfusion.common_ui.theme.FlavorFusionTheme
import org.junit.Rule
import org.junit.Test
import io.mockk.mockk
import io.mockk.verify

class DrinksScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun drinks_screen_displays_drinks() {
        val drinks = listOf(
            DrinkUi("Margarita", "url1", "1"),
            DrinkUi("Mojito", "url2", "2")
        )
        val state = DrinksContract.State(drinks = drinks, searchDrinks = drinks)

        composeTestRule.setContent {
            FlavorFusionTheme {
                DrinksScreen(state = state, onEvent = {})
            }
        }

        composeTestRule.onNodeWithText("Margarita").assertIsDisplayed()
        composeTestRule.onNodeWithText("Mojito").assertIsDisplayed()
    }

    @Test
    fun clicking_search_icon_triggers_event() {
        val onEvent: (DrinksContract.Event) -> Unit = mockk(relaxed = true)
        val state = DrinksContract.State()

        composeTestRule.setContent {
            FlavorFusionTheme {
                DrinksScreen(state = state, onEvent = onEvent)
            }
        }

        composeTestRule.onNodeWithContentDescription("Navigation icon").performClick()
        
        verify { onEvent(DrinksContract.Event.OnSearchClicked) }
    }

    @Test
    fun clicking_drink_item_triggers_event() {
        val onEvent: (DrinksContract.Event) -> Unit = mockk(relaxed = true)
        val drink = DrinkUi("Margarita", "url1", "1")
        val state = DrinksContract.State(drinks = listOf(drink), searchDrinks = listOf(drink))

        composeTestRule.setContent {
            FlavorFusionTheme {
                DrinksScreen(state = state, onEvent = onEvent)
            }
        }

        composeTestRule.onNodeWithText("Margarita").performClick()

        verify { onEvent(DrinksContract.Event.OnDrinkClicked(drink)) }
    }
}