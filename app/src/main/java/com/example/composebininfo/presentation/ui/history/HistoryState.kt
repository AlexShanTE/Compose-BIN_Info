package com.example.composebininfo.presentation.ui.history

import com.example.composebininfo.domain.models.BinHistoryItem

data class HistoryState(
    var binHistoryItemList: List<BinHistoryItem> = emptyList()
)
