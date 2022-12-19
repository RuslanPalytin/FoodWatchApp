package com.example.testwatchapp.presentation.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Typography
import com.example.testwatchapp.R

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)
val Italiano = FontFamily(Font(R.font.italiano))
val Nutino = FontFamily(Font(R.font.nunito_regular))
val Roboto = FontFamily(Font(R.font.roboto_regular))