package com.mygce.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Fixed brand palette on purpose (no Android 12 dynamic colour): the app should look like MyGCE everywhere.
private val LightColors = lightColorScheme(
    primary = BrandBlue,
    onPrimary = Color.White,
    secondary = BrandRed,
    onSecondary = Color.White,
    background = Paper,
    onBackground = Ink,
    surface = Color.White,
    onSurface = Ink,
    surfaceVariant = Color(0xFFE9ECF1),
    onSurfaceVariant = Color(0xFF4A5362),
    outline = Color(0xFFC9CED8),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF7DA2FF),
    onPrimary = Ink,
    secondary = Color(0xFFFF6B7A),
    onSecondary = Ink,
    background = Ink,
    onBackground = Color(0xFFE8ECF2),
    surface = Color(0xFF161B22),
    onSurface = Color(0xFFE8ECF2),
    surfaceVariant = Color(0xFF222934),
    onSurfaceVariant = Color(0xFFA9B2C0),
    outline = Color(0xFF3A4352),
)

private val MyGceShapes = Shapes(
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(20.dp),
    large = RoundedCornerShape(28.dp),
)

@Composable
fun MyGceTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = MyGceTypography,
        shapes = MyGceShapes,
        content = content,
    )
}
