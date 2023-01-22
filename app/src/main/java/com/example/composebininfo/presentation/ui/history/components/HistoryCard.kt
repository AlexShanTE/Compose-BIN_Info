package com.example.composebininfo.presentation.ui.history.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
fun HistoryCardPreview() {
    HistoryCard(
        bin = "4276 2819 3945 2837",
        onCardClick = {},
        onDeleteItemClick = {}
    )
}