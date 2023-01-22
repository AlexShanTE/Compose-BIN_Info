package com.example.composebininfo.presentation.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composebininfo.R
import com.example.composebininfo.presentation.ui.home.components.RequestLayout
import com.example.composebininfo.presentation.ui.main.Screen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val viewModel: HomeViewModel = hiltViewModel()

    val uiState by viewModel.state.collectAsState()
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(1f)
                .height(80.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            RequestLayout(
                modifier = modifier.weight(1f),
                userInput = viewModel.userInput,
                onUserInputChange = { input -> viewModel.updateUserInput(input) },
                isCorrectInput = uiState.isCorrectInput,
                onKeyboardDone = {
                    if (viewModel.checkUserInput(viewModel.userInput)) {
                        viewModel.insert(bin = viewModel.userInput)
                        navController.navigate(Screen.BinInfoScreen.withArgs(viewModel.userInput))
                    } else
                        viewModel.makeToast(context, R.string.wrong_input)
                }
            )
            if (viewModel.userInput.isNotEmpty()) {
                Icon(
                    modifier = modifier
                        .width(40.dp)
                        .clickable { viewModel.clearTextField() },
                    imageVector = Icons.Default.Close,
                    contentDescription = "delete history item"
                )
            }
        }
        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp, top = 100.dp),
            onClick = {
                if (viewModel.checkUserInput(viewModel.userInput)) {
                    viewModel.insert(bin = viewModel.userInput)
                    navController.navigate(Screen.BinInfoScreen.withArgs(viewModel.userInput))
                } else
                    viewModel.makeToast(context, R.string.wrong_input)
            }
        ) {
            Text(stringResource(R.string.get_info))
        }
    }

}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    val context = LocalContext.current
    HomeScreen(navController = NavController(context))
}