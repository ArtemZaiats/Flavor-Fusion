package com.flavorfusion.meals.model

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flavorfusion.common_ui.model.meal.MealUi

class MealsDataPreviewProvider : PreviewParameterProvider<List<MealUi>> {
    override val values: Sequence<List<MealUi>>
        get() = sequenceOf(
            listOf(
                MealUi(
                    mealName = "Beef and Mustard Pie",
                    mealImage = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg",
                    mealId = "1001"
                ),
                MealUi(
                    mealName = "Teriyaki Chicken Casserole",
                    mealImage = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg",
                    mealId = "1002"
                ),
            )
        )
}