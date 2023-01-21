package com.example.composebininfo.presentation.ui.historyscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composebininfo.R
import com.example.composebininfo.presentation.ui.mainscreen.Screen

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
            items(uiState.binHistoryItemList, key = { item -> item.id }) { binHistoryItem ->
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
fun HistoryCard(
    modifier: Modifier = Modifier,
    bin: String,
    onCardClick: (String) -> Unit,
    onDeleteItemClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(vertical = 8.dp)
            .clickable { onCardClick(bin) },
        elevation = 8.dp
    ) {
        Row(
            modifier = modifier
                .height(56.dp)
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = bin,
                modifier = modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .weight(1f),
                textAlign = TextAlign.Center
            )
            Icon(
                modifier = modifier
                    .fillMaxHeight(0.7f)
                    .aspectRatio(1f)
                    .clickable { onDeleteItemClick() },
                imageVector = Icons.Default.Close,
                contentDescription = "delete history item"
            )

        }
    }
}

@Composable
@Preview(showBackground = true)
fun HistoryScreenPreview() {
    val context = LocalContext.current
    HistoryScreen(navController = NavController(context))
}

@Composable
@Preview(showBackground = true)
fun HistoryCardPreview() {
    HistoryCard(
        bin = "4276 2819 3945 2837",
        onCardClick = {},
        onDeleteItemClick = {}
    )
}