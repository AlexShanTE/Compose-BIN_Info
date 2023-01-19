package com.example.composebininfo.presentation.ui.bininfoscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composebininfo.R
import com.example.composebininfo.domain.Bank
import com.example.composebininfo.domain.BinInfo
import com.example.composebininfo.domain.Country
import com.example.composebininfo.domain.Number
import com.example.composebininfo.presentation.ui.bininfoscreen.BinViewModel.Companion.GEO
import com.example.composebininfo.presentation.ui.bininfoscreen.BinViewModel.Companion.PHONE
import com.example.composebininfo.presentation.ui.bininfoscreen.BinViewModel.Companion.URL

@Composable
fun BinInfoScreen(
    modifier: Modifier = Modifier,
    bin: String
) {
    val viewModel: BinViewModel = hiltViewModel()
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
    binInfo: BinInfo,
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
                modifier = modifier.weight(1f)
            ) {
                Text(text = stringResource(R.string.scheme_network))
                Text(text = binInfo.scheme.toString())
                Spacer(modifier = modifier.height(16.dp))
                Text(stringResource(R.string.brand))
                Text(text = binInfo.brand.toString())
                Spacer(modifier = modifier.height(16.dp))
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
                Spacer(modifier = modifier.height(16.dp))
                Text(stringResource(R.string.bank))
                Text(text = binInfo.bank?.name.toString())
                Text(
                    modifier = modifier.clickable {
                        onUrlClick(binInfo.bank?.url)
                    },
                    text = binInfo.bank?.url ?: ""
                )
                Text(
                    modifier = modifier.clickable {
                        onPhoneClick(binInfo.bank?.phone)
                    },
                    text = binInfo.bank?.phone ?: ""
                )
            }
            Spacer(modifier = modifier.width(20.dp))
            Column(
                modifier = modifier.weight(1f)
            ) {
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
                Spacer(modifier = modifier.height(16.dp))
                Text(stringResource(R.string.prepaid))
                Text(
                    buildAnnotatedString {
                        if (binInfo.prepaid == null) append("null")
                        else {
                            withStyle(style = SpanStyle(fontWeight = if (binInfo.prepaid == true) FontWeight.Bold else FontWeight.Normal)) {
                                append(stringResource(R.string.yes))
                            }
                            append("/")
                            withStyle(style = SpanStyle(fontWeight = if (binInfo.prepaid == false) FontWeight.Bold else FontWeight.Normal)) {
                                append(stringResource(R.string.no))
                            }
                        }
                    }
                )
                Spacer(modifier = modifier.height(16.dp))
                Text(stringResource(R.string.country))
                Column {
                    Row {
                        Text(text = binInfo.country?.emoji ?: "")
                        Text(text = binInfo.country?.name.toString())
                    }
                    Column(
                        modifier = modifier.clickable {
                            onCoordinatesClick(
                                binInfo.country?.latitude,
                                binInfo.country?.longitude
                            )
                        }
                    ) {
                        Text(
                            text = if (binInfo.country?.name == null) "" else
                                stringResource(R.string.latitude) + ": ${binInfo.country.latitude}"
                        )
                        Text(
                            text = if (binInfo.country?.name == null) "" else
                                stringResource(R.string.longitude) + ": ${binInfo.country.longitude}"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingLayout(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.loading))
        Spacer(modifier = modifier.height(48.dp))
        CircularProgressIndicator()
    }
}

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

@Composable
@Preview(showBackground = true)
fun BinInfoLayoutPreview() {
    BinInfoLayout(
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
        ),
        onUrlClick = {},
        onCoordinatesClick = { _, _ -> },
        onPhoneClick = {}
    )
}

