package com.example.composebininfo.presentation.ui.history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composebininfo.R
import com.example.composebininfo.presentation.ui.history.components.HistoryCard
import com.example.composebininfo.presentation.ui.main.Screen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val viewModel: HistoryViewModel = hiltViewModel()
    val uiState by viewModel.state.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        LazyColumn(
            modifier = modifier.weight(1f)
        ) {
            items(
                uiState.binHistoryItemList,
                key = { item -> item.id }
            ) { binHistoryItem ->
                HistoryCard(
                    bin = binHistoryItem.bin,
                    onCardClick = {
                        navController.navigate(
                            Screen.BinInfoScreen.withArgs(
                                binHistoryItem.bin
                            )
                        )
                    },
                    onDeleteItemClick = { viewModel.deleteBin(binHistoryItem) },
                    modifier = modifier.animateItemPlacement()
                )
            }
        }
        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 24.dp,
                ),
            onClick = { viewModel.clearHistory() }
        ) {
            Text(stringResource(R.string.clear_history))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HistoryScreenPreview() {
    val context = LocalContext.current
    HistoryScreen(navController = NavController(context))
}

