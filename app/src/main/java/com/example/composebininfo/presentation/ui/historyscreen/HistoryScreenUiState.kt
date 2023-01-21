package com.example.composebininfo.presentation.ui.historyscreen

import com.example.composebininfo.domain.BinHistoryItem

data class HistoryScreenUiState(
    var binHistoryItemList: List<BinHistoryItem> = emptyList()
)
