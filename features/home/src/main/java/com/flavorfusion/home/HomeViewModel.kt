package com.flavorfusion.home

import com.flavorfusion.core_ui.mvi.MviViewModel
import com.flavorfusion.home.compose.FunFactsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val funFactsProvider: FunFactsProvider,
    config: HomeContract.Config
) : MviViewModel<HomeContract.State, HomeContract.Event>(config) {

    init {
        loadFacts()
    }

    override fun handleEvent(event: HomeContract.Event) {}

    private fun loadFacts() {
        val facts = funFactsProvider.provide()
        val randomFacts = facts.shuffled().take(2)
        dispatch(HomeContract.Action.LoadFacts(randomFacts))
    }
}
