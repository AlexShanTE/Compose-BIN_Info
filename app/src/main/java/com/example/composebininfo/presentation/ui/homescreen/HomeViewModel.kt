package com.example.composebininfo.presentation.ui.homescreen

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composebininfo.di.IoDispatcher
import com.example.composebininfo.domain.BinHistoryItem
import com.example.composebininfo.domain.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HistoryRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenUiState())
    val state: StateFlow<HomeScreenUiState> = _state.asStateFlow()

    init {
        _state.value = HomeScreenUiState(userInput = "")
    }

    var userInput by mutableStateOf("")
        private set

    fun updateUserInput(input: String) {
        if (input.isDigitsOnly()) {
            userInput = input
        }
    }

    fun insert(bin: String) {
        viewModelScope.launch(dispatcher) {
            repository.insert(BinHistoryItem(bin = bin))
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
