package com.example.composebininfo.presentation.ui.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composebininfo.R

@Composable
fun ErrorLayout(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onRefreshButtonClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_error),
            contentDescription = stringResource(R.string.error)
        )
        Spacer(modifier = modifier.height(16.dp))
        Text(
            modifier = modifier
                .padding(horizontal = 80.dp)
                .fillMaxWidth(),
            text = errorMessage,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.height(80.dp))
        Button(
            onClick = { onRefreshButtonClick() },
            modifier = modifier
                .height(40.dp)
                .width(180.dp)
        ) {
            Text(text = stringResource(R.string.refresh))
        }
    }
}