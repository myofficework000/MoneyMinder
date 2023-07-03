package com.business.money_minder.util

import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OutlinedSearchBar(
    modifier: Modifier = Modifier,
    value: MutableState<String> = rememberSaveable { mutableStateOf("") },
    enabled: Boolean = true,
    label: (@Composable () -> Unit)? = null,
    debounceTime: Long = 500,
    onChange: (String) -> Unit
) {
    LaunchedEffect(value.value) {
        if (value.value.isBlank()) onChange("")
        else launch {
            delay(debounceTime)
            onChange(value.value)
        }
    }

    OutlinedTextField(
        value = value.value,
        onValueChange = { value.value = it },
        modifier = modifier.then(Modifier.clearFocusOnKeyboardDismiss()),
        enabled = enabled,
        label = label,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = MaterialTheme.colors.onPrimary,
            focusedBorderColor = MaterialTheme.colors.onPrimary,
            focusedLabelColor = MaterialTheme.colors.onPrimary,
            textColor = MaterialTheme.colors.onPrimary
        )
    )
}