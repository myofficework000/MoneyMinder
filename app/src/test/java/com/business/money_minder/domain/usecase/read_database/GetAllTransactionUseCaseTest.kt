package com.business.money_minder.domain.usecase.read_database

import com.business.money_minder.domain.repository.TransactionRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class GetAllTransactionUseCaseTest {
    private lateinit var transactionRepository: TransactionRepository
    private lateinit var getAllTransactionUseCase: GetAllTransactionUseCase

    @Before
    fun setUp() {
        transactionRepository = mock()
        getAllTransactionUseCase = GetAllTransactionUseCase(transactionRepository)
    }

    @Test
    fun `retrieve all transactions correctly`(): Unit = runBlocking {
        getAllTransactionUseCase()
        verify(transactionRepository, times(1)).getAllTransaction()
    }
}