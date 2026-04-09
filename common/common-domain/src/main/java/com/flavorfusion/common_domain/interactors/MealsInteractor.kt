package com.flavorfusion.common_domain.interactors

import com.flavorfusion.common_domain.repositories.MealsRepository
import javax.inject.Inject

class MealsInteractor @Inject constructor(
    private val mealsRepository: MealsRepository
) {
    suspend fun getMealsByCategory(category: String) = mealsRepository.getMealsByCategory(category)
    suspend fun getMealById(id: String) = mealsRepository.getMealById(id)
}