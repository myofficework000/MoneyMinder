package com.business.money_minder.domain.usecase.read_database

import com.business.money_minder.domain.repository.TransactionRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class GetLastMonthTransactionTest {

    private lateinit var transactionRepository: TransactionRepository
    private lateinit var getLastMonthTransaction: GetLastMonthTransaction

    @Before
    fun setUp() {
        transactionRepository = mock()
        getLastMonthTransaction = GetLastMonthTransaction(transactionRepository)
    }

    @Test
    fun `retrieve last month transactions correctly`(): Unit = runBlocking {
        getLastMonthTransaction(anyString())
        verify(transactionRepository, times(1)).getLastMonthTransaction(anyString())
    }
}