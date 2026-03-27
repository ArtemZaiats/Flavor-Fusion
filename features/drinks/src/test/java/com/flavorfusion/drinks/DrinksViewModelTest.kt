package com.flavorfusion.drinks

import androidx.lifecycle.ViewModel
import com.flavorfusion.common_domain.interactors.DrinksInteractor
import com.flavorfusion.common_domain.interactors.SettingsInteractor
import com.flavorfusion.common_domain.model.Result
import com.flavorfusion.common_domain.model.drinks.Drink
import com.flavorfusion.common_ui.Executor
import com.flavorfusion.common_ui.error.ErrorMessageProvider
import com.flavorfusion.common_ui.model.drink.toUi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DrinksViewModelTest {

    private val drinksInteractor: DrinksInteractor = mockk()
    private val settingsInteractor: SettingsInteractor = mockk()
    private val errorMessageProvider: ErrorMessageProvider = mockk()
    
    // Create a real-ish implementation of Executor for the test
    private val executor = object : Executor {
        override fun <T : ViewModel, D> T.launch(
            tag: String,
            context: kotlin.coroutines.CoroutineContext,
            handleActionError: Boolean,
            onDialogAction: (String) -> Unit,
            onSuccess: suspend (D) -> Unit,
            onError: (com.flavorfusion.common_ui.error.ErrorMessage) -> Unit,
            action: suspend CoroutineScope.() -> Result<D>
        ): Job {
            return CoroutineScope(context).launch {
                val result = action()
                if (result is Result.Success) {
                    onSuccess(result.data)
                }
            }
        }
    }
    
    private val config: DrinksContract.Config = DrinksContract.Config()

    private lateinit var viewModel: DrinksViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        coEvery { settingsInteractor.getShowAlcoholicFlow() } returns flowOf(true)
        coEvery { drinksInteractor.getDrinksByAlcoholic(any()) } returns Result.Success(emptyList())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial load gets drinks`() = runTest {
        val drinks = listOf(Drink("Margarita", "url", "1"))
        coEvery { drinksInteractor.getDrinksByAlcoholic(true) } returns Result.Success(drinks)

        viewModel = DrinksViewModel(
            drinksInteractor,
            settingsInteractor,
            errorMessageProvider,
            executor,
            config
        )

        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(drinks.toUi(), viewModel.state.value.drinks)
        coVerify { drinksInteractor.getDrinksByAlcoholic(true) }
    }

    @Test
    fun `search updates searchDrinks state`() = runTest {
        val drinks = listOf(Drink("Margarita", "url", "1"))
        coEvery { drinksInteractor.getDrinksByAlcoholic(true) } returns Result.Success(drinks)
        
        val searchResult = listOf(Drink("Mojito", "url", "2"))
        coEvery { drinksInteractor.getDrinkByNameFlow("Moji") } returns flowOf(Result.Success(searchResult))

        viewModel = DrinksViewModel(
            drinksInteractor,
            settingsInteractor,
            errorMessageProvider,
            executor,
            config
        )
        
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.handleEvent(DrinksContract.Event.OnSearchValueChanged("Moji"))
        
        advanceTimeBy(400)
        testDispatcher.scheduler.advanceUntilIdle()
        
        assertEquals("Moji", viewModel.state.value.searchValue)
        assertEquals(searchResult.toUi(), viewModel.state.value.searchDrinks)
    }

    @Test
    fun `refresh gets drinks again`() = runTest {
        coEvery { drinksInteractor.getDrinksByAlcoholic(true) } returns Result.Success(emptyList())

        viewModel = DrinksViewModel(
            drinksInteractor,
            settingsInteractor,
            errorMessageProvider,
            executor,
            config
        )
        
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.handleEvent(DrinksContract.Event.OnRefresh)
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify(exactly = 2) { drinksInteractor.getDrinksByAlcoholic(true) }
    }
}
