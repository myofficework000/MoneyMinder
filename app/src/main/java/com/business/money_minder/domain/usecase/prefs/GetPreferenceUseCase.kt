package com.business.money_minder.domain.usecase.prefs

import com.business.money_minder.domain.repository.PrefsRepository
import javax.inject.Inject

class GetPreferenceUseCase @Inject constructor(
    private val prefsRepository: PrefsRepository
) {
    operator fun invoke(name: String) =
        prefsRepository.getPref(name)
}