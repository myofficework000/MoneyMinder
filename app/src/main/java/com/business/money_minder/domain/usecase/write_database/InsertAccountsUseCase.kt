package com.business.money_minder.domain.usecase.write_database

import com.business.money_minder.data.local.entity.AccountDto
import com.business.money_minder.domain.repository.TransactionRepository
import javax.inject.Inject

class InsertAccountsUseCase @Inject constructor(private val repo: TransactionRepository) {

    suspend operator fun invoke(account: List<AccountDto>) {
        repo.insertAccount(account)
    }
}