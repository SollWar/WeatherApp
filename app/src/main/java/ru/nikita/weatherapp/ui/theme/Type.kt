package ru.nikita.weatherapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ru.nikita.weatherapp.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val Jura = FontFamily(
    Font(R.font.jura_light, FontWeight.Light),
    Font(R.font.jura_regular, FontWeight.Normal),
    Font(R.font.jura_medium, FontWeight.Medium),
    Font(R.font.jura_bold, FontWeight.Bold),
    Font(R.font.jura_semibold, FontWeight.SemiBold)
)

fun juraFont16sp(
    textAlign: TextAlign = TextAlign.Start,
    color: Color = White
): TextStyle = TextStyle(
    fontSize = 16.sp,
    fontFamily = Jura,
    fontWeight = FontWeight.Light,
    color = color,
    textAlign = textAlign
)

fun juraFont24sp(
    textAlign: TextAlign = TextAlign.Start,
    color: Color = White
): TextStyle = TextStyle(
    fontSize = 24.sp,
    fontFamily = Jura,
    fontWeight = FontWeight.Bold,
    color = color,
    textAlign = textAlign
)

fun juraFont64sp(
    textAlign: TextAlign = TextAlign.Start,
    color: Color = White
): TextStyle = TextStyle(
    fontSize = 64.sp,
    fontFamily = Jura,
    fontWeight = FontWeight.Bold,
    color = color,
    textAlign = textAlign
)

fun juraFont20spGray(
    textAlign: TextAlign = TextAlign.Start,
    color: Color = TextGray
): TextStyle = TextStyle(
    fontSize = 20.sp,
    fontFamily = Jura,
    fontWeight = FontWeight.Light,
    color = color,
    textAlign = textAlign
)

fun juraFont16spGray(
    textAlign: TextAlign = TextAlign.Start,
    color: Color = TextGray
): TextStyle = TextStyle(
    fontSize = 16.sp,
    fontFamily = Jura,
    fontWeight = FontWeight.Light,
    color = color,
    textAlign = textAlign
)

fun juraFont12spGray(
    textAlign: TextAlign = TextAlign.Start,
    color: Color = TextGray
): TextStyle = TextStyle(
    fontSize = 12.sp,
    fontFamily = Jura,
    fontWeight = FontWeight.Light,
    color = color,
    textAlign = textAlign
)