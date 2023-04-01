package com.example.core_ui

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush

data class GradientPalette(
    val primary: Brush,
    val primaryVariant: Brush
)

val LocalGradient = staticCompositionLocalOf<GradientPalette> {
    error("CompositionLocal LocalGradient not present")
}