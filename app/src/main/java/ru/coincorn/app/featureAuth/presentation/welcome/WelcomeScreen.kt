package ru.coincorn.app.featureAuth.presentation.welcome

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.madmaximuus.persian.buttons.PersianButtonDefaults
import io.github.madmaximuus.persian.buttons.PersianPrimaryButton
import io.github.madmaximuus.persian.buttons.PersianSecondaryButton
import io.github.madmaximuus.persian.foundation.extendedColorScheme
import io.github.madmaximuus.persian.foundation.spacing
import ru.coincorn.app.R
import ru.coincorn.app.core.ui.theme.CoinCornTheme

@Composable
fun WelcomeRoute(
    viewModel: WelcomeViewModel = hiltViewModel()
) {
    WelcomeScreen(
        onSignUpClick = viewModel::signUp,
        onSignInClick = viewModel::signIn
    )
}

@Composable
private fun WelcomeScreen(
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        color = MaterialTheme.extendedColorScheme.surface
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
                text = stringResource(id = R.string.welcome_sign_up_button_label),
                sizes = PersianButtonDefaults.largeSizes(),
                onClick = onSignUpClick
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            PersianSecondaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.welcome_sign_in_button_label),
                sizes = PersianButtonDefaults.largeSizes(),
                onClick = onSignInClick
            )
            Spacer(modifier = Modifier.weight(0.4f))
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
                onSignUpClick = {},
                onSignInClick = {}
            )
        }
    }
}