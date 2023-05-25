package com.business.money_minder.domain.usecase.write_database

import com.business.money_minder.data.local.entity.TransactionDto
import com.business.money_minder.domain.repository.TransactionRepository
import javax.inject.Inject

class InsertNewTransactionUseCase @Inject constructor(private val repo: TransactionRepository) {

    suspend operator fun invoke(dailyExpense: TransactionDto) {
        repo.insertTransaction(dailyExpense)
    }
}