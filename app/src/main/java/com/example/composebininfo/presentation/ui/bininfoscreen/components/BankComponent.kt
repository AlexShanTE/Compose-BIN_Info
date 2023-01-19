package com.example.composebininfo.presentation.ui.bininfoscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebininfo.R
import com.example.composebininfo.domain.Bank
import com.example.composebininfo.domain.BinInfoModel
import com.example.composebininfo.domain.Country
import com.example.composebininfo.domain.Number

@Composable
fun BankComponent(
    modifier: Modifier = Modifier,
    binInfo: BinInfoModel,
    onUrlClick: (url: String?) -> Unit,
    onPhoneClick: (phone: String?) -> Unit
) {
    Column() {
        Text(stringResource(R.string.bank))
        Text(text = binInfo.bank?.name.toString())
        if (binInfo.bank?.url !== null) {
            Text(
                modifier = modifier.clickable {
                    onUrlClick(binInfo.bank.url)
                },
                text = binInfo.bank.url,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        if (binInfo.bank?.phone !== null) {
            Text(
                modifier = modifier.clickable {
                    onPhoneClick(binInfo.bank.phone)
                },
                text = binInfo.bank.phone,
                maxLines = 1
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BankComponentPreview() {
    BankComponent(
        binInfo = BinInfoModel(
            bank = Bank(
                city = "Moscow",
                name = "Jyske Bank",
                phone = "+79037351403",
                url = null
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
        ), onUrlClick = {} , onPhoneClick = {} )
}