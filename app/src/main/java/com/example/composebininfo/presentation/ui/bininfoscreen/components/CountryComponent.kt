package com.example.composebininfo.presentation.ui.bininfoscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebininfo.R
import com.example.composebininfo.domain.Bank
import com.example.composebininfo.domain.BinInfoModel
import com.example.composebininfo.domain.Country
import com.example.composebininfo.domain.Number

@Composable
fun CountryComponent(
    modifier: Modifier = Modifier,
    binInfo: BinInfoModel,
    onCoordinatesClick: (latitude: Double?, longitude: Double?) -> Unit
) {
    Column() {
        Text(stringResource(R.string.country))
        Column {
            Row {
                if (binInfo.country?.emoji !== null) {
                    Text(text = binInfo.country.emoji)
                }
                Text(text = binInfo.country?.name.toString())
            }
            if ((binInfo.country?.latitude !== null) && (binInfo.country.longitude !== null)) {
                Column(
                    modifier = modifier.clickable {
                        onCoordinatesClick(binInfo.country.latitude, binInfo.country.longitude)
                    }
                ) {
                    Text(
                        text = if (binInfo.country.name == null) "" else
                            stringResource(R.string.latitude) + ": ${binInfo.country.latitude}"
                    )
                    Text(
                        text = if (binInfo.country.name == null) "" else
                            stringResource(R.string.longitude) + ": ${binInfo.country.longitude}"
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CountryComponentPreview() {
    CountryComponent(
        binInfo = BinInfoModel(
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
    ) { _, _ -> }
}