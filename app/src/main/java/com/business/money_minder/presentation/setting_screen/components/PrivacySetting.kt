package com.business.money_minder.presentation.setting_screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.business.money_minder.R
import com.business.money_minder.util.spacing

@Composable
fun PrivacySetting() {
    TextButton(
        enabled = false, // There's nothing inside for now...
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
            contentColor = MaterialTheme.colors.onSurface,
            // Remove this when this is not disabled.
            disabledBackgroundColor = Color.DarkGray.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(
            horizontal = spacing.medium,
            vertical = 20.dp
        )
    ) {
        Text(
            text = "Privacy Settings",
            style = MaterialTheme.typography.button,
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.onSurface
        )

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Icon(
                painter = painterResource(R.drawable.edit),
                contentDescription = null,
                modifier = Modifier.then(Modifier.size(16.dp))
            )
        }
    }
}