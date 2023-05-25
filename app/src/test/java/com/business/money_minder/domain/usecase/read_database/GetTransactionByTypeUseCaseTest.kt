package com.business.money_minder.domain.usecase.read_database

import com.business.money_minder.domain.repository.TransactionRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class GetTransactionByTypeUseCaseTest {

    private lateinit var transactionRepository: TransactionRepository
    private lateinit var getTransactionByTypeUseCase: GetTransactionByTypeUseCase

    @Before
    fun setUp() {
        transactionRepository = mock()
        getTransactionByTypeUseCase = GetTransactionByTypeUseCase(transactionRepository)
    }

    @Test
    fun `retrieve transactions according to transaction type`(): Unit = runBlocking {
        getTransactionByTypeUseCase(ArgumentMatchers.anyString())
        verify(transactionRepository, times(1)).getTransactionByType(ArgumentMatchers.anyString())
    }
}