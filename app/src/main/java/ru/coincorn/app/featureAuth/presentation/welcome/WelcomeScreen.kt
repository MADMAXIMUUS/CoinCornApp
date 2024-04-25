package ru.coincorn.app.featureAuth.presentation.welcome

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.madmaximuus.persian.buttons.PersianButtonDefaults
import io.github.madmaximuus.persian.buttons.PersianPrimaryButton
import io.github.madmaximuus.persian.dividers.PersianFullWidthHorizontalDivider
import io.github.madmaximuus.persian.foundation.extendedColorScheme
import io.github.madmaximuus.persian.foundation.spacing
import ru.coincorn.app.R
import ru.coincorn.app.core.ui.components.ContinueWithButton
import ru.coincorn.app.core.ui.components.GradientBackground
import ru.coincorn.app.core.ui.theme.CoinCornTheme
import ru.coincorn.app.core.ui.theme.googleBackgroundDark
import ru.coincorn.app.core.ui.theme.googleBorderDark
import ru.coincorn.app.core.ui.theme.googleBorderLight
import ru.coincorn.app.core.ui.theme.googleTitleDark
import ru.coincorn.app.core.ui.theme.googleTitleLight

@Composable
fun WelcomeRoute(
    viewModel: WelcomeViewModel = hiltViewModel()
) {
    WelcomeScreen(
        onContinueWithEmailClick = viewModel::continueWithEmail,
        onContinueWithGoogleClick = viewModel::continueWithGoogle,
        onContinueWithAppleClick = viewModel::continueWithApple,
    )
}

@Composable
private fun WelcomeScreen(
    onContinueWithEmailClick: () -> Unit,
    onContinueWithGoogleClick: () -> Unit,
    onContinueWithAppleClick: () -> Unit,
) {
    GradientBackground(
        modifier = Modifier.fillMaxSize(),
        gradientColors = listOf(
            0f to MaterialTheme.extendedColorScheme.primaryContainer,
            .5f to MaterialTheme.extendedColorScheme.surface
        ),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = MaterialTheme.spacing.large),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(0.3f))
                Image(
                    painter = painterResource(id = R.drawable.img_welcome),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.weight(0.5f))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.welcome_title),
                    color = MaterialTheme.extendedColorScheme.primary,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.welcome_text),
                    color = MaterialTheme.extendedColorScheme.secondary,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraExtraLarge))
                PersianPrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.welcome_continue_email_button_label),
                    leadingIcon = painterResource(id = R.drawable.ic_alternate_email),
                    sizes = PersianButtonDefaults.largeSizes(),
                    onClick = onContinueWithEmailClick
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.spacing.extraExtraLarge),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PersianFullWidthHorizontalDivider(
                        modifier = Modifier
                            .weight(0.5f)
                    )
                    Text(
                        text = stringResource(R.string.or),
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.extraLarge),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.extendedColorScheme.onSurface
                    )
                    PersianFullWidthHorizontalDivider(
                        modifier = Modifier
                            .weight(0.5f)
                    )
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                ContinueWithButton(
                    icon = painterResource(id = R.drawable.ic_google),
                    label = stringResource(id = R.string.welcome_continue_google_button_label),
                    iconColor = Color.Unspecified,
                    labelColor = if (isSystemInDarkTheme()) googleTitleDark else googleTitleLight,
                    borderColor = if (isSystemInDarkTheme()) googleBorderDark else googleBorderLight,
                    backgroundColor = if (isSystemInDarkTheme()) googleBackgroundDark else Color.White,
                    onClick = onContinueWithGoogleClick
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                ContinueWithButton(
                    icon = painterResource(id = R.drawable.ic_apple),
                    label = stringResource(id = R.string.welcome_continue_apple_button_label),
                    iconColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
                    labelColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
                    borderColor = Color.Transparent,
                    backgroundColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    onClick = onContinueWithAppleClick
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraExtraLarge))
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun WelcomeScreenPreview() {
    CoinCornTheme {
        Surface {
            WelcomeScreen(
                onContinueWithEmailClick = {},
                onContinueWithGoogleClick = {},
                onContinueWithAppleClick = {},
            )
        }
    }
}