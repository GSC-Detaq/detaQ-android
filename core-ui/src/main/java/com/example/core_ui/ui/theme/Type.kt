package com.example.core_ui.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.core_ui.R

private val sansFont = FontFamily(
    Font(R.font.worksans_regular),
    Font(R.font.worksans_semibold, weight = FontWeight.SemiBold)
)

val labelTypography = TextStyle(
    fontFamily = sansFont,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp
)

val placeholderTypography = TextStyle(
    fontFamily = sansFont,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
)

val hintTypography = TextStyle(
    fontFamily = sansFont,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
)

val navigationTypography = TextStyle(
    fontFamily = sansFont,
    fontWeight = FontWeight.Normal,
    fontSize = 10.sp
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = sansFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    h2 = TextStyle(
        fontFamily = sansFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    h3 = TextStyle(
        fontFamily = sansFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    h4 = TextStyle(
        fontFamily = sansFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    h5 = TextStyle(
        fontFamily = sansFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = sansFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = sansFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = sansFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    )
)