package ru.coincorn.app.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.madmaximuus.persian.foundation.PersianTheme
import io.github.madmaximuus.persian.foundation.extendedColorScheme
import kotlin.math.tan

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    gradientColors: List<Pair<Float, Color>>,
    content: @Composable () -> Unit,
) {
    Surface(
        color = MaterialTheme.extendedColorScheme.surface,
        modifier = modifier.fillMaxSize(),
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .drawWithCache {
                    val offset = size.height * tan(
                        Math
                            .toRadians(0.0)
                            .toFloat(),
                    )

                    val start = Offset(size.width / 2 + offset / 2, 0f)
                    val end = Offset(size.width / 2 - offset / 2, size.height)

                    val topGradient = Brush.linearGradient(
                        *(gradientColors.toTypedArray()),
                        start = start,
                        end = end,
                    )

                    onDrawBehind {
                        drawRect(topGradient)
                    }
                },
        ) {
            content()
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GradientBackgroundDefault() {
    PersianTheme {
        GradientBackground(
            Modifier.size(100.dp, 200.dp),
            gradientColors = listOf(
                0f to MaterialTheme.extendedColorScheme.primaryContainer,
                .5f to MaterialTheme.extendedColorScheme.primaryContainer,
                .75f to MaterialTheme.extendedColorScheme.surface
            ),
            content = {}
        )
    }
}