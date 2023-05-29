package com.business.money_minder.presentation.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.business.money_minder.R
import com.business.money_minder.util.spacing

@Composable
fun ListPlaceholder(
    label: String =
        "No transaction has been made so far. Tap the '+' button to  get started"
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(
            start = spacing.medium,
            top = spacing.medium,
            end = spacing.medium
        ).fillMaxSize()
    ) {
        Icon(
            painter = painterResource(R.drawable.blank_list),
            tint = MaterialTheme.colors.onBackground,
            contentDescription = "no item added"
        )

        Text(
            text = label,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}