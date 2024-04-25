package ru.coincorn.app.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.madmaximuus.persian.foundation.PersianTheme
import io.github.madmaximuus.persian.foundation.shape
import io.github.madmaximuus.persian.foundation.spacing
import io.github.madmaximuus.persian.iconBox.PersianIconBox
import io.github.madmaximuus.persian.iconBox.PersianIconBoxDefaults
import ru.coincorn.app.R

@Composable
fun ContinueWithButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    label: String,
    iconColor: Color,
    labelColor: Color,
    borderColor: Color,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(MaterialTheme.shape.shape16)
            .background(backgroundColor, MaterialTheme.shape.shape16)
            .border(1.dp, borderColor, MaterialTheme.shape.shape16)
            .clickable(
                enabled = true,
                onClick = onClick,
                role = Role.Button,
                indication = rememberRipple(
                    color = labelColor
                ),
                interactionSource = remember { MutableInteractionSource() }
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CompositionLocalProvider(LocalContentColor provides iconColor) {
            PersianIconBox(
                icon = icon,
                size = PersianIconBoxDefaults.size24()
            )
        }
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
        Text(
            text = label,
            color = labelColor,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ContinueWithPreview() {
    PersianTheme {
        Surface {
            ContinueWithButton(
                modifier = Modifier.padding(MaterialTheme.spacing.extraExtraLarge),
                icon = painterResource(id = R.drawable.ic_apple),
                label = "Continue with Apple",
                iconColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
                labelColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
                borderColor = Color.Transparent,
                backgroundColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                onClick = {}
            )
        }
    }
}