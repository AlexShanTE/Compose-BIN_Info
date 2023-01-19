package com.example.composebininfo.presentation.ui.bininfoscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebininfo.R
import com.example.composebininfo.domain.Bank
import com.example.composebininfo.domain.BinInfoModel
import com.example.composebininfo.domain.Country
import com.example.composebininfo.domain.Number

@Composable
fun CardNumberComponent(
    modifier: Modifier = Modifier,
    binInfo: BinInfoModel
){
    Column {
        Text(stringResource(R.string.card_number))
        Row {
            Column {
                Text(stringResource(R.string.length))
                Text(text = binInfo.number?.length.toString())
            }
            Spacer(modifier = modifier.width(16.dp))
            Column {
                Text(stringResource(R.string.luhn))
                Text(
                    buildAnnotatedString {
                        if (binInfo.number?.luhn == null) append("null")
                        else {
                            withStyle(style = SpanStyle(fontWeight = if (binInfo.number.luhn == true) FontWeight.Bold else FontWeight.Normal)) {
                                append(stringResource(R.string.yes))
                            }
                            append("/")
                            withStyle(style = SpanStyle(fontWeight = if (binInfo.number.luhn == false) FontWeight.Bold else FontWeight.Normal)) {
                                append(stringResource(R.string.no))
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardNumberComponentPreview() {
    CardNumberComponent(
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
    )
}