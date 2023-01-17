package com.example.composebininfo.presentation.ui.requestscreen

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.example.composebininfo.domain.BinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor(
    private val repository: BinRepository
) : ViewModel() {

    private val _requestScreenUiState = MutableStateFlow(RequestScreenUiState())
    val requestScreenUiState: StateFlow<RequestScreenUiState> = _requestScreenUiState.asStateFlow()

    init {
        _requestScreenUiState.value = RequestScreenUiState(userInput = "")
    }

    var userInput by mutableStateOf("")
        private set

    fun updateUserInput(input: String) {
        if (input.isDigitsOnly()) {
            userInput = input
        }
    }

    fun getInfo() {
        val isCorrectInput = checkUserInput(input = userInput)
        if (isCorrectInput) repository.getInfo(userInput)
    }

    private fun checkUserInput(input: String): Boolean {
        return if (input.length < 4) {
            _requestScreenUiState.update { state -> state.copy(isIncorrectInput = true) }
            true
        } else {
            _requestScreenUiState.update { state -> state.copy(isIncorrectInput = false) }
            false
        }
    }

    fun makeToast(context: Context, @StringRes text: Int) {
        Toast.makeText(
            context, text, Toast.LENGTH_SHORT
        ).show()
    }
}
