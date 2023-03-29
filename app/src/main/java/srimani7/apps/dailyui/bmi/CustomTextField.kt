@file:OptIn(ExperimentalMaterial3Api::class)

package srimani7.apps.dailyui.bmi

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CustomTextField(state: ValueState,imeAction: ImeAction, onValueChange: (String) -> Unit, ) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = state.value,
        isError = state.error != null,
        supportingText = { state.error?.let { Text(it) } },
        label = { Text(state.label) },
        suffix = { Text(state.prefix) },
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = imeAction),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        })
    )
}

data class ValueState(
    val label: String,
    val prefix: String,
    val value: String = "",
    val error: String? = null
) {
    fun toNumber() = value.toDoubleOrNull()
}