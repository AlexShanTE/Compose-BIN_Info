package com.example.composebininfo.presentation.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composebininfo.domain.models.Bank
import com.example.composebininfo.domain.models.Bin
import com.example.composebininfo.domain.models.Country
import com.example.composebininfo.domain.models.Number
import com.example.composebininfo.presentation.ui.details.DetailsViewModel.Companion.GEO
import com.example.composebininfo.presentation.ui.details.DetailsViewModel.Companion.PHONE
import com.example.composebininfo.presentation.ui.details.DetailsViewModel.Companion.URL
import com.example.composebininfo.presentation.ui.details.components.BankComponent
import com.example.composebininfo.presentation.ui.details.components.BrandComponent
import com.example.composebininfo.presentation.ui.details.components.CardNumberComponent
import com.example.composebininfo.presentation.ui.details.components.CountryComponent
import com.example.composebininfo.presentation.ui.details.components.ErrorLayout
import com.example.composebininfo.presentation.ui.details.components.LoadingLayout
import com.example.composebininfo.presentation.ui.details.components.PrepaidComponent
import com.example.composebininfo.presentation.ui.details.components.SchemeComponent
import com.example.composebininfo.presentation.ui.details.components.TypeComponent

@Composable
fun DetailsScreen(
    bin: String
) {
    val viewModel: DetailsViewModel = hiltViewModel()
    val uiState by viewModel.state.collectAsState()

    val context = LocalContext.current

    when (uiState.status) {
        is State.Loading -> LoadingLayout()
        is State.Loaded -> BinInfoLayout(
            binInfo = viewModel.binInfo,
            onCoordinatesClick = { latitude, longitude ->
                viewModel.startActivityWithIntent(
                    context,
                    type = GEO,
                    data = if (latitude == null || longitude == null) "null"
                    else "$latitude, $longitude"
                )
            },
            onUrlClick = { url ->
                viewModel.startActivityWithIntent(context, type = URL, data = url.toString())
            },
            onPhoneClick = { phone ->
                viewModel.startActivityWithIntent(context, type = PHONE, data = phone.toString())
            }
        )
        is State.Error -> ErrorLayout(errorMessage = (uiState.status as State.Error).error) {
            viewModel.getInfo(bin)
        }
        is State.Idle -> viewModel.getInfo(bin)
    }

}

@Composable
fun BinInfoLayout(
    modifier: Modifier = Modifier,
    binInfo: Bin,
    onCoordinatesClick: (latitude: Double?, longitude: Double?) -> Unit,
    onUrlClick: (url: String?) -> Unit,
    onPhoneClick: (phone: String?) -> Unit
) {
    Card(
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = modifier.weight(1.5f)
            ) {
                SchemeComponent(binInfo = binInfo)
                Spacer(modifier = modifier.height(16.dp))
                BrandComponent(binInfo = binInfo)
                Spacer(modifier = modifier.height(16.dp))
                CardNumberComponent(binInfo = binInfo)
                Spacer(modifier = modifier.height(16.dp))
                BankComponent(
                    binInfo = binInfo,
                    onPhoneClick = onPhoneClick,
                    onUrlClick = onUrlClick
                )
            }
            Spacer(modifier = modifier.width(40.dp))
            Column(
                modifier = modifier.weight(1f)
            ) {
                TypeComponent(binInfo = binInfo)
                Spacer(modifier = modifier.height(16.dp))
                PrepaidComponent(binInfo = binInfo)
                Spacer(modifier = modifier.height(16.dp))
                CountryComponent(
                    binInfo = binInfo,
                    onCoordinatesClick = onCoordinatesClick
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BinInfoLayoutPreview() {
    BinInfoLayout(
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
        ),
        onUrlClick = {},
        onCoordinatesClick = { _, _ -> },
        onPhoneClick = {}
    )
}

