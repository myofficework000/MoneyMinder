package com.business.money_minder.domain.usecase.read_datastore

import com.business.money_minder.domain.repository.DatastoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLimitDurationUseCase @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) {
    suspend operator fun invoke(): Flow<Int> {
        return datastoreRepository.readLimitDurationFromDataStore()
    }
}