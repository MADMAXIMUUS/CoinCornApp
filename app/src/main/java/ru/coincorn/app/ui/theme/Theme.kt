package ru.coincorn.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import io.github.madmaximuus.persian.foundation.PersianTheme
import io.github.madmaximuus.persian.foundation.darkColorScheme
import io.github.madmaximuus.persian.foundation.lightColorScheme

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFBACF8),
    onPrimary = Color(0xFF521457),
    primaryContainer = Color(0xFF6C2D70),
    onPrimaryContainer = Color(0xFFFFD6FA),
    secondary = Color(0xFFD9BFD4),
    onSecondary = Color(0xFF3C2B3B),
    secondaryContainer = Color(0xFF544152),
    onSecondaryContainer = Color(0xFFF6DBF0),
    tertiary = Color(0xFFF6B8AB),
    onTertiary = Color(0xFF4C261D),
    tertiaryContainer = Color(0xFF663B32),
    onTertiaryContainer = Color(0xFFFFDAD3),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    correct = Color(0xFF6CDBAB),
    onCorrect = Color(0xFF003825),
    correctContainer = Color(0xFF005138),
    onCorrectContainer = Color(0xFF89F8C6),
    outline = Color(0xFF998D96),
    background = Color(0xFF1E1A1D),
    onBackground = Color(0xFFE9E0E4),
    surface = Color(0xFF161215),
    onSurface = Color(0xFFCCC4C8),
    surfaceVariant = Color(0xFF4D444B),
    onSurfaceVariant = Color(0xFFD0C3CC),
    inverseSurface = Color(0xFFE9E0E4),
    inverseOnSurface = Color(0xFF1E1A1D),
    inversePrimary = Color(0xFF874589),
    surfaceTint = Color(0xFFFBACF8),
    scrim = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF874589),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFD6FA),
    onPrimaryContainer = Color(0xFF37003C),
    secondary = Color(0xFF6C586A),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFF6DBF0),
    onSecondaryContainer = Color(0xFF261625),
    tertiary = Color(0xFF825248),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFDAD3),
    onTertiaryContainer = Color(0xFF33110A),
    error = Color(0xFFBA1A1A),
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    correct = Color(0xFF006C4B),
    onCorrect = Color.White,
    correctContainer = Color(0xFF89F8C6),
    onCorrectContainer = Color(0xFF002114),
    outline = Color(0xFF7F747C),
    background = Color(0xFFFFFBFF),
    onBackground = Color(0xFF1E1A1D),
    surface = Color(0xFFFFF7FA),
    onSurface = Color(0xFF1E1A1D),
    surfaceVariant = Color(0xFFEDDEE8),
    onSurfaceVariant = Color(0xFF4D444B),
    inverseSurface = Color(0xFF342F32),
    inverseOnSurface = Color(0xFFF7EEF2),
    inversePrimary = Color(0xFFFBACF8),
    surfaceTint = Color(0xFF874589),
    scrim = Color.Black
)

@Composable
fun CoinCornTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        val currentWindow = (view.context as? Activity)?.window
            ?: throw Exception("Not in an activity - unable to get Window reference")
        SideEffect {
            currentWindow.statusBarColor = Color.Transparent.toArgb()
            currentWindow.navigationBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(currentWindow, view).isAppearanceLightStatusBars =
                !darkTheme
            WindowCompat.getInsetsController(currentWindow, view).isAppearanceLightNavigationBars =
                !darkTheme
        }
    }

    PersianTheme(
        dynamicColor = dynamicColor,
        lightColors = LightColorScheme,
        darkColors = DarkColorScheme,
        darkTheme = darkTheme,
        content = content
    )
}