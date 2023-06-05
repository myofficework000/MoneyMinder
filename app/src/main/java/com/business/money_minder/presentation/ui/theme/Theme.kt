package com.business.money_minder.presentation.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.business.money_minder.presentation.main.MainViewModel
import com.business.money_minder.util.CompactSpacing
import com.business.money_minder.util.ExpandedSpacing
import com.business.money_minder.util.LocalSpacing
import com.business.money_minder.util.MediumSpacing
import com.business.money_minder.util.WindowInfo
import com.business.money_minder.util.rememberWindowInfo
import kotlinx.coroutines.InternalCoroutinesApi

private val DarkColorPalette = darkColors(
    primary = RoyalBlue,
    secondary = Indigo900,
    background = DeepPurple900,
    surface = DeepPurple300,
    error = Red200,
    onSurface = White,
    onPrimary = Peach,
    onSecondary = Color.Gray,
    onBackground = Color.White
)

private val LightColorPalette = lightColors(
    primary = LightBlue500,
    secondary = GreenAlpha700,
    background = Grey100,
    surface = White,
    error = Red500,
    onSurface = Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.Black
)

@OptIn(InternalCoroutinesApi::class)
@Composable
fun MoneyMinderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    mainViewModel: MainViewModel,
    content: @Composable () -> Unit
) {
    val isThemeModeReversed by mainViewModel.isThemeModeReversed.collectAsState()

    val colors = (
        if (darkTheme xor isThemeModeReversed) {
            DarkColorPalette
        } else {
            LightColorPalette
        }
    ).canAnimate()



    val windowInfo = rememberWindowInfo()

    CompositionLocalProvider(
        when (windowInfo.screenHeightInfo) {
            is WindowInfo.WindowType.Compact -> {
                LocalSpacing provides CompactSpacing()
            }
            is WindowInfo.WindowType.Medium -> {
                LocalSpacing provides MediumSpacing()
            }
            else -> LocalSpacing provides ExpandedSpacing()
        }
    ) {
        MaterialTheme(
            colors = colors,
            typography = typography,
            shapes = Shapes,
            content = content
        )
    }
}

@Composable
private fun animateColor(targetValue: Color) =
    animateColorAsState(
        targetValue = targetValue,
        animationSpec = tween(durationMillis = 1000)
    ).value

@Composable
private fun Colors.canAnimate() = copy(
    primary = animateColor(primary),
    primaryVariant = animateColor(primaryVariant),
    secondary = animateColor(secondary),
    secondaryVariant = animateColor(secondaryVariant),
    background = animateColor(background),
    surface = animateColor(surface),
    error = animateColor(error),
    onPrimary = animateColor(onPrimary),
    onSecondary = animateColor(onSecondary),
    onBackground = animateColor(onBackground),
    onSurface = animateColor(onSurface),
    onError = animateColor(onError)
)