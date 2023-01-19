package com.example.composebininfo.presentation.ui.requestscreen

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RequestViewModel : ViewModel() {

    private val _state = MutableStateFlow(RequestScreenUiState())
    val state: StateFlow<RequestScreenUiState> = _state.asStateFlow()

    init {
        _state.value = RequestScreenUiState(userInput = "")
    }

    var userInput by mutableStateOf("")
        private set

    fun updateUserInput(input: String) {
        if (input.isDigitsOnly()) {
            userInput = input
        }
    }

    fun checkUserInput(input: String): Boolean {
        return if (input.length < 4) {
            _state.update { state -> state.copy(isCorrectInput = false) }
            false
        } else {
            _state.update { state -> state.copy(isCorrectInput = true) }
            true
        }
    }

    fun makeToast(context: Context, @StringRes text: Int) {
        Toast.makeText(
            context, text, Toast.LENGTH_SHORT
        ).show()
    }
}
