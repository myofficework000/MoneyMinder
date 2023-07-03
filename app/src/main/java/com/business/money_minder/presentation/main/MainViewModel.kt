package com.business.money_minder.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.business.money_minder.common.Constants
import com.business.money_minder.domain.usecase.prefs.GetPreferenceUseCase
import com.business.money_minder.domain.usecase.prefs.SetPreferenceUseCase
import com.business.money_minder.domain.usecase.read_datastore.GetOnBoardingKeyUseCase
import com.business.money_minder.presentation.navigation.Screen
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@InternalCoroutinesApi
class MainViewModel @Inject constructor(
    private val getOnBoardingKeyUseCase: GetOnBoardingKeyUseCase,
    private val getPreferenceUseCase: GetPreferenceUseCase,
    private val setPreferenceUseCase: SetPreferenceUseCase
) : ViewModel() {

    var isLoading = MutableStateFlow(true)
        private set

    var startDestination = MutableStateFlow(Screen.WelcomeScreen.route)
        private set

    var isThemeModeReversed = MutableStateFlow(false)
        private set

    init {
        viewModelScope.launch(IO) {
            getOnBoardingKeyUseCase().collect { completed ->
                if (completed)
                    startDestination.value = Screen.HomeScreen.route
            }

            isLoading.value = false
        }

        isThemeModeReversed.value = getPreferenceUseCase(Constants.PREF_THEME) == "true"
    }

    fun reverseTheme(switchedOn: Boolean) {
        setPreferenceUseCase(
            Constants.PREF_THEME,
            if (switchedOn) "true" else "false"
        )

        isThemeModeReversed.value = switchedOn
    }
}