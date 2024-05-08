package com.ellamzoughi.restcountriesexplorer.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ellamzoughi.restcountriesexplorer.R
import com.ellamzoughi.restcountriesexplorer.model.CountryBean
import com.ellamzoughi.restcountriesexplorer.ui.CustomError
import com.ellamzoughi.restcountriesexplorer.viewmodel.MainViewModel
import androidx.compose.ui.graphics.Color
import com.ellamzoughi.restcountriesexplorer.ui.CustomButton

@Composable
fun SearchScreen(mainViewModel: MainViewModel, navController: NavHostController) {
    var searchText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val errorMessage = mainViewModel.errorMessage.value
    val isLoading by remember { mainViewModel.runInProgress }
    var isInputFocused by remember { mutableStateOf(false) }
    val countryList = mainViewModel.countryList
    var searchPerformed by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.simple_world_map),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        if (!(!isInputFocused && !isLoading)) {
            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.4f))
                    .matchParentSize()
            )
        }

        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Enter country name") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .onFocusChanged { focusState ->
                        isInputFocused = focusState.isFocused
                    },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    if (!isLoading) {
                        searchPerformed = true
                        mainViewModel.searchCountryByName(searchText)
                        focusManager.clearFocus()
                    }
                }),
                textStyle = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            val buttonWidth = Modifier.width(180.dp)

            CustomButton(
                onClick = {
                    if (!isLoading) {
                        searchPerformed = true
                        mainViewModel.searchCountryByName(searchText)
                        focusManager.clearFocus()
                    }
                },
                text = "Search Country",
                modifier = buttonWidth,
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomButton(
                onClick = {
                    searchPerformed = true
                    mainViewModel.loadAllCountries()
                },
                text = "Load All Countries",
                modifier = buttonWidth,
                enabled = !isLoading
            )

            CustomError(modifier = Modifier.padding(top = 8.dp), errorMessage = errorMessage)

            if (isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 6.dp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (countryList.isEmpty() && !isLoading && searchPerformed) {
                Text("No countries found.")
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    items(countryList.size) { index ->
                        CountryRowItem(country = countryList[index]) {
                            navController.navigate("detail/$index")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CountryRowItem(country: CountryBean, onClick: () -> Unit) {
    val isDarkTheme = androidx.compose.foundation.isSystemInDarkTheme()
    val textColor = if (isDarkTheme) Color.White else Color.Black

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${country.flag} ${country.name.common}",
            style = MaterialTheme.typography.titleMedium.copy(
                color = textColor,
            )
        )
    }
}
