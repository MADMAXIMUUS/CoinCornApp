package ru.coincorn.app.core.ui.theme

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
    surface = Color(0xFF161215),
    onSurface = Color(0xFFCCC4C8),
    surfaceVariant = Color(0xFF4D444B),
    onSurfaceVariant = Color(0xFFD0C3CC),
    surfaceTint = Color(0xFFFBACF8),
    scrim = Color.Black,
    surface1 = Color(0xFF211A20),
    surface2 = Color(0xFF281E27),
    surface3 = Color(0xFF2F232E),
    surface4 = Color(0xFF312430),
    surface5 = Color(0xFF362835)
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
    valid = Color(0xFF006C4B),
    onValid = Color.White,
    validContainer = Color(0xFF89F8C6),
    onValidContainer = Color(0xFF002114),
    outline = Color(0xFF7F747C),
    surface = Color(0xFFFFF7FA),
    onSurface = Color(0xFF1E1A1D),
    surfaceVariant = Color(0xFFEDDEE8),
    onSurfaceVariant = Color(0xFF4D444B),
    surfaceTint = Color(0xFF874589),
    scrim = Color.Black,
    surface1 = Color(0xFFF9EEF4),
    surface2 = Color(0xFFF5E9F1),
    surface3 = Color(0xFFF2E3EE),
    surface4 = Color(0xFFF1E2EC),
    surface5 = Color(0xFFEEDEEA)
)

@Composable
fun CoinCornTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        val currentWindow = (view.context as? Activity)?.window
            ?: throw Exception("Not in an activity - unable to get Window reference")
        SideEffect {
            currentWindow.statusBarColor = Color.Transparent.toArgb()
            currentWindow.navigationBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(currentWindow, view)
                .isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(currentWindow, view)
                .isAppearanceLightNavigationBars = !darkTheme
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