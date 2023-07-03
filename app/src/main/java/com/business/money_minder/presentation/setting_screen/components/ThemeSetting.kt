package com.business.money_minder.presentation.setting_screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.business.money_minder.R
import com.business.money_minder.util.spacing
import kotlin.reflect.KFunction1

@Composable
fun ThemeSetting(
    isThemeReversed: Boolean,
    reverseThemeFunc: KFunction1<Boolean,Unit>
) {
    TextButton(
        onClick = {
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = spacing.medium,
                vertical = spacing.small
            ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.DarkGray.copy(alpha = 0.1f),
            contentColor = MaterialTheme.colors.onSurface
        ),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(
            horizontal = spacing.medium,
            vertical = 20.dp
        )
    ) {
        Text(
            text = stringResource(R.string.change_theme),
            style = MaterialTheme.typography.button,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start
        )

        Switch(modifier = Modifier.padding(end = spacing.small), switch = isThemeReversed) { switched ->
            reverseThemeFunc(switched)
        }
    }
}