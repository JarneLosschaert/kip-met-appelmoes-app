package be.howest.jarnelosschaert.kipmetappelmoes.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Purple,
    primaryVariant = Purple,
    onPrimary = Black,
    secondary = Blue,
    secondaryVariant = Green,
    onSecondary = Black,
    background = DarkGrey,
    onBackground = White,
    surface = Purple,
    onSurface = Black
)

private val LightColorPalette = lightColors(
    primary = Purple,
    primaryVariant = DarkPurple,
    onPrimary = White,
    secondary = Blue,
    secondaryVariant = Green,
    onSecondary = White,
    background = White,
    onBackground = Black,
    surface = Purple,
    onSurface = White
)

/* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */

@Composable
fun KipMetAppelmoesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}