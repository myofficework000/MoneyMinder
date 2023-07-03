package com.business.money_minder.util

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import com.business.money_minder.presentation.home_screen.CategoryItem
import kotlin.reflect.KClass


@OptIn(ExperimentalLayoutApi::class)
fun Modifier.clearFocusOnKeyboardDismiss(): Modifier = composed {
    var isFocused by remember { mutableStateOf(false) }
    val imeIsVisible = WindowInsets.isImeVisible
    val focusManager = LocalFocusManager.current

    LaunchedEffect(imeIsVisible) {
        if (!imeIsVisible && isFocused) focusManager.clearFocus()
    }

    onFocusEvent {
        if (isFocused != it.isFocused) isFocused = it.isFocused
    }
}

fun<C: Enum<*>> KClass<C>.toCategory() =
    this.java.enumConstants?.map { it as CategoryItem }?.toTypedArray() ?: arrayOf()
