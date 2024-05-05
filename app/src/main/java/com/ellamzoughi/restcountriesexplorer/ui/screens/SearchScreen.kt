package com.ellamzoughi.restcountriesexplorer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ellamzoughi.restcountriesexplorer.MainViewModel
import com.ellamzoughi.restcountriesexplorer.model.CountryBean
import androidx.compose.ui.Alignment


@Composable
fun SearchScreen(mainViewModel: MainViewModel) {
    Column(modifier = Modifier.padding(8.dp)) {
        if (mainViewModel.runInProgress.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize().weight(10f)
        ) {
            items(mainViewModel.countryList.size) { index ->
                CountryRowItem(country = mainViewModel.countryList[index])
            }
        }

        Button(onClick = { mainViewModel.loadAllCountries() }) {
            Text("Load All Countries")
        }
    }
}

@Composable
fun CountryRowItem(country: CountryBean) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text("Name: ${country.name.common}")
        Text("Capital: ${country.capital?.joinToString(", ") ?: "N/A"}")
        Text("Population: ${country.population}")
    }
}
