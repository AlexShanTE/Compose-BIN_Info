package com.example.composebininfo.presentation.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composebininfo.di.IoDispatcher
import com.example.composebininfo.domain.models.BinHistoryItem
import com.example.composebininfo.domain.repositories.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: HistoryRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryState())
    val state: StateFlow<HistoryState> = _state.asStateFlow()

    init {
        viewModelScope.launch(dispatcher) {
            repository.getHistory().collect {
                _state.update { state -> state.copy(binHistoryItemList = it) }
            }
        }
    }

    fun deleteBin(binHistoryItem: BinHistoryItem) {
        viewModelScope.launch(dispatcher) {
            repository.delete(binHistoryItem)
            val newList = state.value.binHistoryItemList.filter { it.id != binHistoryItem.id }
            _state.update { state ->
                state.copy(
                    binHistoryItemList = newList
                )
            }
        }
    }

    fun clearHistory() {
        viewModelScope.launch(dispatcher) {
            state.value.binHistoryItemList.forEach {
                deleteBin(it)
            }
        }
    }

}