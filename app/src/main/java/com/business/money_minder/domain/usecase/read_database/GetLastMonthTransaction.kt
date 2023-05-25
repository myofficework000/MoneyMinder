package com.business.money_minder.domain.usecase.read_database

import com.business.money_minder.data.local.entity.TransactionDto
import com.business.money_minder.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLastMonthTransaction @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(transactionType: String) : Flow<List<TransactionDto>> {
        return transactionRepository.getLastMonthTransaction(transactionType)
    }
}