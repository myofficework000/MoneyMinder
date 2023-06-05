package com.business.money_minder.presentation.setting_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.business.money_minder.presentation.main.MainViewModel
import com.business.money_minder.presentation.setting_screen.components.CurrencySetting
import com.business.money_minder.presentation.setting_screen.components.EraseContent
import com.business.money_minder.presentation.setting_screen.components.EraseSetting
import com.business.money_minder.presentation.setting_screen.components.LimitContent
import com.business.money_minder.presentation.setting_screen.components.LimitSetting
import com.business.money_minder.presentation.setting_screen.components.PrivacySetting
import com.business.money_minder.presentation.setting_screen.components.RateSetting
import com.business.money_minder.presentation.setting_screen.components.ReminderSetting
import com.business.money_minder.presentation.setting_screen.components.ThemeSetting
import com.business.money_minder.presentation.setting_screen.components.VersionSetting
import com.business.money_minder.util.spacing
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun SettingScreen(
    settingViewModel: SettingViewModel = hiltViewModel(),
    navController: NavController,
    mainViewModel: MainViewModel
) {

    val currency by settingViewModel.currency.collectAsState()
    val isThemeModeReversed by mainViewModel.isThemeModeReversed.collectAsState()

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()
    val sheetRankState = remember {
        mutableStateOf(0)
    }
    ModalBottomSheetLayout(
        sheetContent = {
            Box(Modifier.defaultMinSize(minHeight = 1.dp)) {
                when (sheetRankState.value) {
                    1 -> {
                        LimitContent(modalBottomSheetState, scope)
                    }

                    2 -> {
                        EraseContent(modalBottomSheetState, scope)
                    }
                }
            }
        },

        sheetState = modalBottomSheetState,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Surface(color = MaterialTheme.colors.background) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Settings",
                        style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.W700),
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = spacing.medium,
                                vertical = spacing.small
                            ),
                        textAlign = TextAlign.Start
                    )

                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                    ) {

                        CurrencySetting(currency, navController)

                        LimitSetting(modalBottomSheetState, scope) {
                            sheetRankState.value = it
                        }

                        ReminderSetting()

                        ThemeSetting(
                            isThemeModeReversed,
                            mainViewModel::reverseTheme
                        )

                        EraseSetting(modalBottomSheetState, scope) {
                            sheetRankState.value = it
                        }

                        Text(
                            text = "App",
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = spacing.medium,
                                    vertical = spacing.medium
                                ),
                            letterSpacing = TextUnit(0.2f, TextUnitType.Sp),
                            textAlign = TextAlign.Start
                        )

                        RateSetting()

                        PrivacySetting()

                        VersionSetting()
                    }
                }
            }
        }
    }
}
