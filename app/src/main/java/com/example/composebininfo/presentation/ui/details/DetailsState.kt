package com.example.composebininfo.presentation.ui.details

import com.example.composebininfo.domain.models.Bin

data class DetailsState(
    val status: State = State.Loading
)

sealed interface State {
    object Loading : State
    data class Error(val error: String) : State
    data class Loaded(val binInfo: Bin) : State
    object Idle:State
}