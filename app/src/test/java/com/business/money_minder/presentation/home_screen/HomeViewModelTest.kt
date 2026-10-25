package com.business.money_minder.presentation.home_screen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.business.money_minder.data.repository.FakeTransactionRepository
import com.business.money_minder.domain.repository.DatastoreRepository
import com.business.money_minder.domain.repository.TransactionRepository
import com.business.money_minder.domain.usecase.GetDateUseCase
import com.business.money_minder.domain.usecase.GetFormattedDateUseCase
import com.business.money_minder.domain.usecase.read_database.GetAccountUseCase
import com.business.money_minder.domain.usecase.read_database.GetAccountsUseCase
import com.business.money_minder.domain.usecase.read_database.GetAllTransactionUseCase
import com.business.money_minder.domain.usecase.read_database.GetCurrentDayExpTransactionUseCase
import com.business.money_minder.domain.usecase.read_database.GetDailyTransactionUseCase
import com.business.money_minder.domain.usecase.read_database.GetMonthlyExpTransactionUse
import com.business.money_minder.domain.usecase.read_database.GetWeeklyExpTransactionUseCase
import com.business.money_minder.domain.usecase.read_datastore.GetCurrencyUseCase
import com.business.money_minder.domain.usecase.read_datastore.GetExpenseLimitUseCase
import com.business.money_minder.domain.usecase.read_datastore.GetLimitDurationUseCase
import com.business.money_minder.domain.usecase.read_datastore.GetLimitKeyUseCase
import com.business.money_minder.domain.usecase.write_database.InsertAccountsUseCase
import com.business.money_minder.domain.usecase.write_database.InsertNewTransactionUseCase
import com.business.money_minder.domain.usecase.write_database.MockitoHelper.anyObject
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var transactionRepository: TransactionRepository
    private lateinit var datastoreRepository: DatastoreRepository
    private val limitDuration = MutableStateFlow(0)

    private lateinit var getDateUseCase: GetDateUseCase
    private lateinit var getFormattedDateUseCase: GetFormattedDateUseCase
    private lateinit var insertNewTransactionUseCase: InsertNewTransactionUseCase
    private lateinit var insertAccountsUseCase: InsertAccountsUseCase
    private lateinit var getDailyTransactionUseCase: GetDailyTransactionUseCase
    private lateinit var allTransactionUseCase: GetAllTransactionUseCase
    private lateinit var getAccountUseCase: GetAccountUseCase
    private lateinit var getAccountsUseCase: GetAccountsUseCase
    private lateinit var getCurrencyUseCase: GetCurrencyUseCase
    private lateinit var getExpenseLimitUseCase: GetExpenseLimitUseCase
    private lateinit var getLimitDurationUseCase: GetLimitDurationUseCase
    private lateinit var getLimitKeyUseCase: GetLimitKeyUseCase
    private lateinit var getCurrentDayExpTransactionUseCase: GetCurrentDayExpTransactionUseCase
    private lateinit var getWeeklyExpTransactionUseCase: GetWeeklyExpTransactionUseCase
    private lateinit var getMonthlyExpTransactionUse: GetMonthlyExpTransactionUse

    @Before
    fun setUp() {
        transactionRepository = FakeTransactionRepository()
        datastoreRepository = mock()

        getDateUseCase = mock()
        getFormattedDateUseCase = mock()
        insertNewTransactionUseCase = InsertNewTransactionUseCase(transactionRepository)
        insertAccountsUseCase = InsertAccountsUseCase(transactionRepository)
        getDailyTransactionUseCase = GetDailyTransactionUseCase(transactionRepository)
        allTransactionUseCase = GetAllTransactionUseCase(transactionRepository)
        getAccountUseCase = GetAccountUseCase(transactionRepository)
        getAccountsUseCase = GetAccountsUseCase(transactionRepository)
        getCurrencyUseCase = GetCurrencyUseCase(datastoreRepository)
        getExpenseLimitUseCase = GetExpenseLimitUseCase(datastoreRepository)
        getLimitKeyUseCase = GetLimitKeyUseCase(datastoreRepository)
        getLimitDurationUseCase = GetLimitDurationUseCase(datastoreRepository)
        getCurrentDayExpTransactionUseCase =
            GetCurrentDayExpTransactionUseCase(transactionRepository)
        getWeeklyExpTransactionUseCase = GetWeeklyExpTransactionUseCase(transactionRepository)
        getMonthlyExpTransactionUse = GetMonthlyExpTransactionUse(transactionRepository)

        `when`(getDateUseCase())
            .thenReturn("2022-04-28")

        `when`(getFormattedDateUseCase.invoke(anyObject()))
            .thenReturn("Thu 28, Apr")

        runBlocking {
            `when`(getCurrencyUseCase.invoke()).thenReturn(
                flow {
                    emit("NGN")
                }
            )
            `when`(datastoreRepository.readLimitDurationFromDataStore()).thenReturn(limitDuration)
            `when`(datastoreRepository.readLimitKeyFromDataStore()).thenReturn(MutableStateFlow(false))
        }

        homeViewModel = HomeViewModel(
            getDateUseCase,
            getFormattedDateUseCase,
            insertNewTransactionUseCase,
            insertAccountsUseCase,
            getDailyTransactionUseCase,
            allTransactionUseCase,
            getAccountUseCase,
            getAccountsUseCase,
            getCurrencyUseCase,
            getExpenseLimitUseCase,
            getLimitDurationUseCase,
            getLimitKeyUseCase,
            getCurrentDayExpTransactionUseCase,
            getWeeklyExpTransactionUseCase,
            getMonthlyExpTransactionUse
        )
    }

    @Test
    fun `expense total follows the selected limit period and its updates`() = runBlocking {
        val repository = transactionRepository as FakeTransactionRepository
        val transaction = repository.dailyExpenses.value.first()
        repository.dailyExpenses.value = listOf(transaction.copy(amount = 10.0))
        repository.weeklyExpenses.value = listOf(transaction.copy(amount = 20.0))
        repository.monthlyExpenses.value = listOf(transaction.copy(amount = 30.0))

        suspend fun awaitTotal(expected: Double) = withTimeout(5_000) {
            homeViewModel.currentExpenseAmount.first { it == expected }
        }

        awaitTotal(10.0)
        limitDuration.value = 1
        awaitTotal(20.0)
        repository.dailyExpenses.value = listOf(transaction.copy(amount = 99.0))
        repository.weeklyExpenses.value = listOf(transaction.copy(amount = 25.0))
        awaitTotal(25.0)
        limitDuration.value = 2
        awaitTotal(30.0)
        Unit
    }

    @Test
    fun `when homeviewmodel is created, retrieve daily transactions`() =
        runBlocking {
            homeViewModel.dailyTransaction.test {
                val initialValue = awaitItem()
                val dailyTrx = if (initialValue.isEmpty()) awaitItem() else initialValue
                assertThat(dailyTrx.size).isGreaterThan(0)
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `when homeviewmodel is created, retrieve all transactions`() = runBlocking {
        homeViewModel.monthlyTransaction.test {
            val initialValue = awaitItem()
            val allTrx = if (initialValue.isEmpty()) awaitItem() else initialValue
            assertThat(allTrx.size).isGreaterThan(0)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `when homeviewmodel is created, retrieve selected currency`() = runBlocking {
        homeViewModel.selectedCurrencyCode.test {
            val initialValue = awaitItem()
            val currency = if (initialValue.isEmpty()) awaitItem() else initialValue
            assertThat(currency).isNotEmpty()
            cancelAndConsumeRemainingEvents()

        }
    }

    @Test
    fun `when homeviewmodel is created, retrieve all income info`() = runBlocking {
        homeViewModel.totalIncome.test {
            val initialValue = awaitItem()
            val income = if (initialValue == 0.0) awaitItem() else initialValue
            assertThat(income).isEqualTo(30.0)
        }
    }

    @Test
    fun `when homeviewmodel is created, retrieve all expense info`() = runBlocking {
        homeViewModel.totalExpense.test {
            val initialValue = awaitItem()
            val expense = if (initialValue == 0.0) awaitItem() else initialValue
            assertThat(expense).isEqualTo(15.0)
        }
    }
}
