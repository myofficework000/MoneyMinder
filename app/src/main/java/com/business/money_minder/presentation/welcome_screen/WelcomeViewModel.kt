package com.business.money_minder.presentation.welcome_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.business.money_minder.data.local.entity.AccountDto
import com.business.money_minder.domain.model.CurrencyModel
import com.business.money_minder.domain.usecase.GetCurrencyUseCase
import com.business.money_minder.domain.usecase.write_database.InsertAccountsUseCase
import com.business.money_minder.domain.usecase.write_datastore.EditCurrencyUseCase
import com.business.money_minder.domain.usecase.write_datastore.EditOnBoardingUseCase
import com.business.money_minder.presentation.home_screen.Account
import com.business.money_minder.presentation.welcome_screen.components.OnBoardingPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val editOnBoardingUseCase: EditOnBoardingUseCase,
    private val editCurrencyUseCase: EditCurrencyUseCase,
    private val insertAccountsUseCase: InsertAccountsUseCase,
    getCurrencyUseCase: GetCurrencyUseCase
) : ViewModel() {

    var countryCurrencies = mutableStateOf(emptyMap<Char, List<CurrencyModel>>())
        private set

    var countryCurrenciesFiltered = mutableStateOf(emptyMap<Char, List<CurrencyModel>>())
        private set

    init {
        countryCurrencies.value = getCurrencyUseCase().groupBy { it.country[0] }
    }

    val listOfPages: State<List<OnBoardingPage>> = mutableStateOf(listOf(
        OnBoardingPage.FirstPage,
        OnBoardingPage.SecondPage,
        OnBoardingPage.ThirdPage
    ))

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(IO) {
            editOnBoardingUseCase(completed = completed)
        }
    }

    fun saveCurrency(currency: String) {
        viewModelScope.launch(IO) {
            editCurrencyUseCase(currency)
        }
    }

    fun createAccounts() {
        viewModelScope.launch(IO) {
            insertAccountsUseCase.invoke(
                listOf(
                    AccountDto(1, Account.CASH.title, 0.0, 0.0, 0.0),
                    AccountDto(2, Account.BANK.title, 0.0, 0.0, 0.0),
                    AccountDto(3, Account.CARD.title, 0.0, 0.0, 0.0)
                )
            )
        }
    }

    fun filterSearchResult(keywords: String) {
        countryCurrenciesFiltered.value = if (keywords.isBlank())
            countryCurrencies.value
        else
            countryCurrencies.value.filterKeys {
                it.lowercaseChar() == keywords.first().lowercaseChar()
            }.toMutableMap().apply {
                forEach { kvPair ->
                    this[kvPair.key] = kvPair.value.filter { item ->
                        item.country.lowercase().contains(keywords.lowercase())
                    }
                }
            }
    }
}