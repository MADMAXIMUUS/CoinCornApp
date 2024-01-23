package ru.coincorn.app.featureVerification.presentation.verify

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.madmaximuus.persian.buttons.PersianButtonDefaults
import io.github.madmaximuus.persian.buttons.PersianPrimaryButton
import io.github.madmaximuus.persian.buttons.PersianSecondaryButton
import io.github.madmaximuus.persian.forms.PersianForm
import io.github.madmaximuus.persian.forms.PersianFormCaptionConfig
import io.github.madmaximuus.persian.forms.PersianFormContent
import io.github.madmaximuus.persian.foundation.extendedColorScheme
import io.github.madmaximuus.persian.foundation.spacing
import io.github.madmaximuus.persian.topAppBar.PersianTopAppBar
import io.github.madmaximuus.persian.topAppBar.PersianTopAppBarMiddle
import ru.coincorn.app.R
import ru.coincorn.app.core.ui.theme.CoinCornTheme

@Composable
fun VerifyRoute(
    viewModel: VerifyViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    VerifyScreen(
        state = state,
        onCodeInput = viewModel::updateCode,
        onResendClick = viewModel::resendEmail,
        onVerifyClick = viewModel::verify
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun VerifyScreen(
    state: VerifyScreenState,
    onCodeInput: (String, Int) -> Unit,
    onResendClick: () -> Unit,
    onVerifyClick: () -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.extendedColorScheme.surface,
        topBar = {
            PersianTopAppBar(
                middle = PersianTopAppBarMiddle.Title(
                    text = stringResource(R.string.verify_title)
                ),
                actionItemsCount = 0
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            Text(
                modifier = Modifier.padding(
                    top = MaterialTheme.spacing.small,
                    start = MaterialTheme.spacing.large,
                    end = MaterialTheme.spacing.large
                ),
                text = stringResource(id = R.string.verify_header),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.extendedColorScheme.onSurface
            )
            Text(
                modifier = Modifier.padding(
                    top = MaterialTheme.spacing.small,
                    start = MaterialTheme.spacing.large,
                    end = MaterialTheme.spacing.large
                ),
                text = emailText(state.email),
                style = MaterialTheme.typography.bodyMedium
            )
            PersianForm(
                modifier = Modifier.padding(
                    top = MaterialTheme.spacing.small,
                    start = MaterialTheme.spacing.large,
                    end = MaterialTheme.spacing.large
                ),
                content = PersianFormContent.SixDigitCodeInput(
                    values = state.code,
                    onValueChange = onCodeInput
                ),
                caption = PersianFormCaptionConfig(text = "")
            )
            PersianSecondaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = MaterialTheme.spacing.medium,
                        start = MaterialTheme.spacing.large,
                        end = MaterialTheme.spacing.large
                    ),
                sizes = PersianButtonDefaults.largeSizes(),
                text = stringResource(id = R.string.resend_email_pattern_button_label, state.timer),
                enabled = state.resendEnabled,
                loading = state.resendLoading,
                onClick = onResendClick
            )
            PersianPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = MaterialTheme.spacing.extraLarge,
                        start = MaterialTheme.spacing.large,
                        end = MaterialTheme.spacing.large
                    ),
                sizes = PersianButtonDefaults.largeSizes(),
                text = stringResource(id = R.string.verify_button_label),
                loading = state.verifyLoading,
                onClick = onVerifyClick
            )
        }
    }
}

@Composable
fun emailText(email: String): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = SpanStyle(color = MaterialTheme.extendedColorScheme.secondary)) {
            append(stringResource(id = R.string.send_message))
        }
        withStyle(style = SpanStyle(color = MaterialTheme.extendedColorScheme.primary)) {
            append(email)
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerifyScreenPreview() {
    CoinCornTheme {
        Surface {
            VerifyScreen(
                state = VerifyScreenState(
                    email = "example@example.com",
                    timer = "04:59"
                ),
                onCodeInput = { _, _ -> },
                onResendClick = {},
                onVerifyClick = {}
            )
        }
    }
}