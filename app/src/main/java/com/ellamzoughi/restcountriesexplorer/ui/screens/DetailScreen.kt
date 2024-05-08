package com.ellamzoughi.restcountriesexplorer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ellamzoughi.restcountriesexplorer.model.CountryBean
import java.util.Locale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.ellamzoughi.restcountriesexplorer.ui.CustomButton
import java.text.NumberFormat

@Composable
fun DetailScreen(country: CountryBean, navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomButton(
            onClick = { navController.navigateUp() },
            text = "Back"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = country.flag,
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = country.name.common.uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = buildAnnotatedString {
                withStyle(style = MaterialTheme.typography.bodyLarge.toSpanStyle().copy(fontWeight = FontWeight.SemiBold)) {
                    append("Official Name: ")
                }
                append(country.name.official)
            }
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = MaterialTheme.typography.bodyLarge.toSpanStyle().copy(fontWeight = FontWeight.SemiBold)) {
                    append("Capital: ")
                }
                append(country.capital?.joinToString(", ") ?: "N/A")
            }
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = MaterialTheme.typography.bodyLarge.toSpanStyle().copy(fontWeight = FontWeight.SemiBold)) {
                    append("Population: ")
                }
                append(NumberFormat.getNumberInstance(Locale.US).format(country.population))
            }
        )
    }
}
