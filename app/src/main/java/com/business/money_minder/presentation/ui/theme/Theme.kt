package com.business.money_minder.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.business.money_minder.util.CompactSpacing
import com.business.money_minder.util.ExpandedSpacing
import com.business.money_minder.util.LocalSpacing
import com.business.money_minder.util.MediumSpacing
import com.business.money_minder.util.WindowInfo
import com.business.money_minder.util.rememberWindowInfo

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

@Composable
fun MoneyMinderTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

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