package com.ellamzoughi.restcountriesexplorer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = DarkPurple,
    secondary = DarkPink,
    tertiary = DarkGrey,
    background = Color.Black,
    surface = DarkGrey,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = LightPurple,
    secondary = LightPink,
    tertiary = LightGrey,
    background = Color.White,
    surface = LightGrey,
    onPrimary = DarkPurple,
    onSecondary = DarkPink,
    onTertiary = DarkGrey,
    onBackground = DarkGrey,
    onSurface = DarkGrey
)

@Composable
fun RestCountriesExplorerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
