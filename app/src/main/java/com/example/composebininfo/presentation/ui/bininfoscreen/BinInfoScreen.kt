package com.example.composebininfo.presentation.ui.bininfoscreen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BinInfoScreen(
    modifier: Modifier = Modifier,
    bin: String,
) {
    Text(text = bin)
}

@Composable
@Preview(showBackground = true)
fun BinInfoScreenPreview() {
    BinInfoScreen(bin = "12345")
}