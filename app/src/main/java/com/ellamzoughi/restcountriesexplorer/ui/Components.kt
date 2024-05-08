package com.ellamzoughi.restcountriesexplorer.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
// import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ellamzoughi.restcountriesexplorer.ui.theme.ErrorColor

@Composable
fun CustomButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textColor: Color = Color.White,
    fontWeight: FontWeight = FontWeight.SemiBold
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        Text(
            text = text,
            color = textColor,
            fontWeight = fontWeight
        )
    }
}

@Composable
fun CustomError(modifier: Modifier = Modifier, errorMessage: String?) {
    AnimatedVisibility(visible = !errorMessage.isNullOrBlank()) {
        Column(
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = errorMessage ?: "",
                color = ErrorColor,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(all = 12.dp)
            )
        }
    }
}
