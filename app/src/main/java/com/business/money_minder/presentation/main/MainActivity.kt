package com.business.money_minder.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.core.view.WindowCompat
import com.business.money_minder.presentation.navigation.MainScreen
import com.business.money_minder.presentation.ui.theme.MoneyMinderTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@ExperimentalComposeUiApi
@InternalCoroutinesApi
@AndroidEntryPoint
@ExperimentalPagerApi
@ExperimentalUnitApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This plus the systemBarsPadding() below is necessary for
        //      WindowInsets.isImeVisible to work. And it is for unfocusing
        //      textboxes when keyboard is closed.
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MoneyMinderTheme(mainViewModel = mainViewModel) {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.systemBarsPadding()
                ) {
                    val destination by mainViewModel.startDestination.collectAsState()

                    MainScreen(
                        startDestination = destination,
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }
}