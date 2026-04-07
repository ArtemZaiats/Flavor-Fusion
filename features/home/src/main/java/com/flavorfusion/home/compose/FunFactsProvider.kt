package com.flavorfusion.home.compose

import com.flavorfusion.common_ui.model.FunFactUi
import javax.inject.Inject

class FunFactsProvider @Inject constructor() {

    fun provide(): List<FunFactUi> = listOf(
        FunFactUi(
            emoji = "🍕",
            title = "Pizza Origin",
            description = "Pizza was invented in Naples, Italy, in the 18th century. The word 'pizza' first appeared in a Latin text from 997 AD in southern Italy."
        ),
        FunFactUi(
            emoji = "🍹",
            title = "Cocktail History",
            description = "The word 'cocktail' first appeared in print in 1806. It was defined as a stimulating liquor composed of spirits, sugar, water, and bitters."
        ),
        FunFactUi(
            emoji = "🌶️",
            title = "Spice Science",
            description = "Capsaicin, the compound that makes chili peppers hot, triggers the same pain receptors as actual heat — your brain literally thinks your mouth is on fire."
        ),
        FunFactUi(
            emoji = "🍫",
            title = "Chocolate & Mood",
            description = "Chocolate contains phenylethylamine, a chemical your brain produces when you're in love. It also has theobromine, a mild stimulant that improves mood."
        ),
        FunFactUi(
            emoji = "🍋",
            title = "Citrus & Cocktails",
            description = "Fresh citrus juice completely transforms a cocktail. The difference between fresh-squeezed and bottled lemon juice can make or break a classic Whiskey Sour."
        ),
        FunFactUi(
            emoji = "🧂",
            title = "Salt Enhances Flavor",
            description = "A pinch of salt in sweet dishes like chocolate cake or caramel suppresses bitterness and amplifies sweetness — professional chefs use this trick constantly."
        ),
        FunFactUi(
            emoji = "🍵",
            title = "Tea Cocktails",
            description = "Tea-infused spirits are a growing cocktail trend. Cold-brew tea can be steeped directly in alcohol to extract flavor without bitterness from heat."
        ),
        FunFactUi(
            emoji = "🥘",
            title = "Umami Discovery",
            description = "Umami, the fifth taste (alongside sweet, sour, salty, and bitter), was discovered by Japanese chemist Kikunae Ikeda in 1908 while studying kelp broth."
        )
    )
}
