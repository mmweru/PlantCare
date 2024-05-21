package com.example.plantcareai.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.plantcareai.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.happy_monkey)),
        fontWeight = FontWeight(200),
        fontSize = 12.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
    )
)
