package com.example.composebininfo.presentation.ui.bininfoscreen

import com.example.composebininfo.domain.BinInfo

data class BinInfoUiState(
    val status: State = State.Loading
)

sealed interface State {
    object Loading : State
    data class Error(val error: String) : State
    data class Loaded(val binInfo: BinInfo) : State
    object Idle:State
}