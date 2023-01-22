package com.example.composebininfo.presentation.ui.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebininfo.R
import com.example.composebininfo.domain.models.Bank
import com.example.composebininfo.domain.models.Bin
import com.example.composebininfo.domain.models.Country
import com.example.composebininfo.domain.models.Number

@Composable
fun TypeComponent(
    modifier: Modifier = Modifier,
    binInfo: Bin
){
    Column() {
        Text(text = stringResource(R.string.type))
        Text(
            buildAnnotatedString {
                if (binInfo.type == null) append("null")
                else {
                    withStyle(style = SpanStyle(fontWeight = if (binInfo.type == "debit") FontWeight.Bold else FontWeight.Normal)) {
                        append(stringResource(R.string.debit))
                    }
                    append("/")
                    withStyle(style = SpanStyle(fontWeight = if (binInfo.type == "credit") FontWeight.Bold else FontWeight.Normal)) {
                        append(stringResource(R.string.credit))
                    }
                }
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TypeComponentPreview() {
    TypeComponent(
        binInfo = Bin(
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