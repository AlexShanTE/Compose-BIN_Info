package com.example.composebininfo.presentation.ui.requestscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composebininfo.R
import com.example.composebininfo.presentation.ui.mainscreen.Screen

@Composable
fun RequestScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val requestViewModel: RequestViewModel = hiltViewModel()

    val uiState by requestViewModel.state.collectAsState()
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        RequestLayout(
            userInput = requestViewModel.userInput,
            onUserInputChange = { input -> requestViewModel.updateUserInput(input) },
            isCorrectInput = uiState.isCorrectInput ,
            onKeyboardDone = {
               if (requestViewModel.checkUserInput(requestViewModel.userInput))
                   navController.navigate(Screen.BinInfoScreen.withArgs(requestViewModel.userInput))
               else
                   requestViewModel.makeToast(context,R.string.wrong_input)
            }
        )
        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 80.dp),
            onClick = {
                if (requestViewModel.checkUserInput(requestViewModel.userInput))
                    navController.navigate(Screen.BinInfoScreen.withArgs(requestViewModel.userInput))
                else
                    requestViewModel.makeToast(context,R.string.wrong_input)
            }
        ) {
            Text(stringResource(R.string.get_info))
        }
    }

}

@Composable
fun RequestLayout(
    modifier: Modifier = Modifier,
    userInput: String,
    onUserInputChange: (String) -> Unit,
    isCorrectInput: Boolean,
    onKeyboardDone: () -> Unit
) {
    OutlinedTextField(
        value = userInput,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        onValueChange = onUserInputChange,
        label = { Text(stringResource(R.string.enter_your_bin)) },
        isError = !isCorrectInput,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { onKeyboardDone() }
        ),
    )
}

@Composable
@Preview(showBackground = true)
fun RequestScreenPreview() {
    val context = LocalContext.current
    RequestScreen(navController = NavController(context))
}