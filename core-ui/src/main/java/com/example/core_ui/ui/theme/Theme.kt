package com.example.core_ui.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.core_ui.GradientPalette
import com.example.core_ui.LocalGradient

private val DarkColorPalette = darkColors(
    primary = Red50,
    primaryVariant = Red70,
    secondary = Secondary
)

private val LightColorPalette = lightColors(
    primary = Red50,
    primaryVariant = Red70,
    secondary = Secondary
)

private val DarkGradientPalette = GradientPalette(
    primary = Linear6050,
    primaryVariant = Radial6050
)

private val LightGradientPalette = GradientPalette(
    primary = Linear6050,
    primaryVariant = Radial6050
)

@Composable
fun DetaQTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val gradients = if (darkTheme) {
        DarkGradientPalette
    } else {
        LightGradientPalette
    }

    CompositionLocalProvider(LocalGradient provides gradients) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}