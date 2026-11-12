package com.mygce.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.mygce.app.R

/** Outfit (SIL OFL), bundled so text looks the same offline on every phone. third_party/outfit/. */
val Outfit = FontFamily(
    Font(R.font.outfit_regular, FontWeight.Normal),
    Font(R.font.outfit_semibold, FontWeight.SemiBold),
    Font(R.font.outfit_bold, FontWeight.Bold),
)

private val base = Typography()

val MyGceTypography = Typography(
    displayLarge = base.displayLarge.copy(fontFamily = Outfit),
    displayMedium = base.displayMedium.copy(fontFamily = Outfit),
    displaySmall = base.displaySmall.copy(fontFamily = Outfit),
    headlineLarge = base.headlineLarge.copy(fontFamily = Outfit, fontWeight = FontWeight.Bold),
    headlineMedium = base.headlineMedium.copy(fontFamily = Outfit, fontWeight = FontWeight.Bold),
    headlineSmall = base.headlineSmall.copy(fontFamily = Outfit, fontWeight = FontWeight.SemiBold),
    titleLarge = base.titleLarge.copy(fontFamily = Outfit, fontWeight = FontWeight.SemiBold),
    titleMedium = base.titleMedium.copy(fontFamily = Outfit, fontWeight = FontWeight.SemiBold),
    titleSmall = base.titleSmall.copy(fontFamily = Outfit, fontWeight = FontWeight.SemiBold),
    bodyLarge = base.bodyLarge.copy(fontFamily = Outfit),
    bodyMedium = base.bodyMedium.copy(fontFamily = Outfit),
    bodySmall = base.bodySmall.copy(fontFamily = Outfit),
    labelLarge = base.labelLarge.copy(fontFamily = Outfit, fontWeight = FontWeight.SemiBold),
    labelMedium = base.labelMedium.copy(fontFamily = Outfit, fontWeight = FontWeight.SemiBold),
    labelSmall = base.labelSmall.copy(fontFamily = Outfit, fontWeight = FontWeight.SemiBold),
)
