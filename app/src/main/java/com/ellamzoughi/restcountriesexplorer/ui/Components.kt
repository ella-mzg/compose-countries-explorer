package com.ellamzoughi.restcountriesexplorer.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ellamzoughi.restcountriesexplorer.ui.theme.RestCountriesExplorerTheme

@Preview(showBackground = true, showSystemUi = true)
@Preview(
     uiMode = Configuration.UI_MODE_NIGHT_YES,
    locale = "FR"
)
@Composable
fun ErrorPreview() {
    RestCountriesExplorerTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column {
                Text(text = "With text:")
                MyError(errorMessage = "This is an Error Message.")
                Text(text = "With spaces:")
                MyError(errorMessage = "   ")
                Text(text = "Empty:")
                MyError(errorMessage = "")
                Text(text = "Null:")
                MyError(errorMessage = null)
            }

        }
    }
}

@Composable
fun MyError(modifier: Modifier = Modifier, errorMessage: String?) {
    AnimatedVisibility(!errorMessage.isNullOrBlank()) {
        Text(
            text = errorMessage ?: "",
            color = MaterialTheme.colorScheme.onError,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.error)
                .padding(6.dp)
        )
    }
}