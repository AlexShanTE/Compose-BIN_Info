package com.example.composebininfo.presentation.ui.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.composebininfo.R

@Composable
fun RequestLayout(
    modifier: Modifier = Modifier,
    userInput: String,
    onUserInputChange: (String) -> Unit,
    isCorrectInput: Boolean,
    onKeyboardDone: () -> Unit
) {
    OutlinedTextField(
        value = userInput,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        onValueChange = onUserInputChange,
        label = { Text(stringResource(R.string.enter_your_bin)) },
        isError = !isCorrectInput,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(
            onDone = { onKeyboardDone() }
        )
    )
}