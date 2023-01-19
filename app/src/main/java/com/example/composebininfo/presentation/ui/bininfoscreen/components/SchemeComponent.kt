package com.example.composebininfo.presentation.ui.bininfoscreen.components
import androidx.compose.foundation.layout.Column
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
fun SchemeComponent(
    modifier: Modifier = Modifier,
    binInfo: BinInfoModel
){
    Column() {
        Text(text = stringResource(R.string.scheme_network))
        Text(text = binInfo.scheme.toString())
    }
}

@Composable
@Preview(showBackground = true)
fun SchemeComponentPreview() {
    SchemeComponent(
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