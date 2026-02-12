package com.flavorfusion.drinks.model

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flavorfusion.common_ui.model.drink.DrinkUi

class DrinksDataPreviewProvider : PreviewParameterProvider<List<DrinkUi>> {
    override val values: Sequence<List<DrinkUi>>
        get() = sequenceOf(
            listOf(
                DrinkUi(
                    drinkName = "Beer",
                    drinkImage = "https://www.thecocktaildb.com/images/media/drink/osgvxt1513595509.jpg",
                    drinkId = "1001"
                ),

                DrinkUi(
                    drinkName = "Beer",
                    drinkImage = "https://www.thecocktaildb.com/images/media/drink/wwqrsw1441248662.jpg",
                    drinkId = "1001"
                )
            )
        )
}