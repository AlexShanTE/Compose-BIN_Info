package com.example.composebininfo.presentation.ui.bininfoscreen

import com.example.composebininfo.domain.BinInfoModel

data class BinInfoUiState(
    val status: State = State.Loading
)

sealed interface State {
    object Loading : State
    data class Error(val error: String) : State
    data class Loaded(val binInfo: BinInfoModel) : State
    object Idle:State
}