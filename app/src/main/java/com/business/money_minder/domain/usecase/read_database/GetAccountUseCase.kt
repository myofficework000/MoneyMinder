package com.business.money_minder.domain.usecase.read_database

import com.business.money_minder.data.local.entity.AccountDto
import com.business.money_minder.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(private val repo: TransactionRepository) {

    operator fun invoke(account: String): Flow<AccountDto> {
        return repo.getAccount(account)
    }
}