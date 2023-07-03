package com.business.money_minder.presentation.home_screen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.business.money_minder.presentation.home_screen.HomeViewModel
import com.business.money_minder.presentation.home_screen.TabButton
import com.business.money_minder.presentation.ui.theme.Peach
import com.business.money_minder.util.spacing

@Composable
fun TabButton(
    tabs: Array<TabButton> = TabButton.values(),
    cornerRadius: Dp = 24.dp,
    onButtonClick: () -> Unit = { },
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val selectedTab by homeViewModel.tabButton.collectAsState()
    Surface(
        modifier = Modifier.padding(
            start = spacing.small,
            end = spacing.small,
            top = spacing.extraSmall
        ),
        color = Peach.copy(alpha = 0.1f),
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = spacing.extraSmall,
                    end = spacing.extraSmall
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            tabs.forEach { tab ->
                val backgroundColor by animateColorAsState(
                    if (selectedTab == tab) MaterialTheme.colors.primary
                    else Color.Transparent,
                    animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
                )

                val textColor by animateColorAsState(
                    if (selectedTab == tab) MaterialTheme.colors.onPrimary
                    else MaterialTheme.colors.onBackground,
                    animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
                )

                TextButton(
                    onClick = {
                        homeViewModel.selectTabButton(tab)
                        onButtonClick()
                    },
                    modifier = Modifier
                        .padding(horizontal = spacing.small)
                        .weight(1f),
                    shape = RoundedCornerShape(cornerRadius),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = backgroundColor,
                        contentColor = textColor
                    )
                ) {
                    Text(
                        text = tab.title,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .padding(
                                horizontal = spacing.extraSmall,
                                vertical = spacing.extraSmall
                            )
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}