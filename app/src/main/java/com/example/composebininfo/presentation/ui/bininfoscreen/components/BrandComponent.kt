package com.example.composebininfo.presentation.ui.bininfoscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebininfo.R
import com.example.composebininfo.domain.Bank
import com.example.composebininfo.domain.BinInfo
import com.example.composebininfo.domain.Country
import com.example.composebininfo.domain.Number

@Composable
fun BrandComponent(
    modifier: Modifier = Modifier,
    binInfo: BinInfo
) {
    Column() {
        Text(stringResource(R.string.brand))
        Text(text = binInfo.brand.toString())
    }
}

@Composable
@Preview(showBackground = true)
fun BrandComponent() {
    BrandComponent(
        binInfo = BinInfo(
            bank = Bank(
                city = "Moscow",
                name = "Jyske Bank",
                phone = "+79037351403",
                url = "http://www.blablabla.com"
            ),
            brand = "Visa/Dankort",
            country = Country(
                alpha2 = "DK",
                currency = "DKK",
                latitude = 56.0,
                longitude = 10.0,
                name = "Denmark",
                numeric = "208",
                emoji = "🇩🇰"
            ),
            number = Number(length = 16, luhn = true),
            prepaid = false,
            scheme = "visa",
            type = "debit"
        )
    )
}