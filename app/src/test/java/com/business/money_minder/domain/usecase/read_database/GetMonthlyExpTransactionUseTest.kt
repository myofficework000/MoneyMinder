package com.business.money_minder.domain.usecase.read_database

import com.business.money_minder.domain.repository.TransactionRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class GetMonthlyExpTransactionUseTest {
    private lateinit var transactionRepository: TransactionRepository
    private lateinit var getMonthlyExpTransactionUse: GetMonthlyExpTransactionUse

    @Before
    fun setUp() {
        transactionRepository = mock()
        getMonthlyExpTransactionUse = GetMonthlyExpTransactionUse(transactionRepository)
    }

    @Test
    fun `retrieve monthly expenses transactions correctly`(): Unit = runBlocking {
        getMonthlyExpTransactionUse()
        verify(transactionRepository, times(1)).getMonthlyExpTransaction()
    }
}