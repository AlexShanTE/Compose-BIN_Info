package com.example.composebininfo.presentation.ui.bininfoscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composebininfo.di.IoDispatcher
import com.example.composebininfo.domain.BinInfo
import com.example.composebininfo.domain.BinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BinViewModel @Inject constructor(
    private val repository: BinRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(BinInfoUiState())
    val state: StateFlow<BinInfoUiState> = _state.asStateFlow()

    var binInfo by mutableStateOf(BinInfo())
        private set

    init {
        _state.value = BinInfoUiState(State.Idle)
    }

    fun getInfo(bin: String) {
        viewModelScope.launch {
            withContext(dispatcher) {
                _state.update { state -> state.copy(status = State.Loading) }
                try {
                    binInfo = repository.getInfo(bin)
                    _state.update { state -> state.copy(status = State.Loaded(binInfo)) }
                } catch (error: Throwable) {
                    val message = error.message ?: error.toString()
                    _state.update { state -> state.copy(status = State.Error(message)) }
                }
            }
        }
    }
}