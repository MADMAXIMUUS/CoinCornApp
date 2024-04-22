package ru.coincorn.app.featureAuth.presentation.authEnterEmail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.madmaximuus.persian.buttons.PersianButtonDefaults
import io.github.madmaximuus.persian.buttons.PersianPrimaryButton
import io.github.madmaximuus.persian.forms.PersianForm
import io.github.madmaximuus.persian.forms.PersianFormCaptionConfig
import io.github.madmaximuus.persian.forms.PersianFormContent
import io.github.madmaximuus.persian.foundation.extendedColorScheme
import io.github.madmaximuus.persian.foundation.spacing
import io.github.madmaximuus.persian.topAppBar.PersianTopAppBar
import io.github.madmaximuus.persian.topAppBar.PersianTopAppBarLeft
import ru.coincorn.app.R
import ru.coincorn.app.core.ui.theme.CoinCornTheme

@Composable
fun AuthEnterEmailRoute(
    viewModel: AuthEnterEmailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    AuthEnterEmailScreen(
        state = state,
        onBackClick = viewModel::back,
        onEmailInput = viewModel::updateEmail,
        onSendCodeClick = viewModel::sendCode,
        onTermsClick = {},
        onPolicyClick = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AuthEnterEmailScreen(
    state: AuthEnterEmailScreenState,
    onBackClick: () -> Unit,
    onEmailInput: (String) -> Unit,
    onSendCodeClick: () -> Unit,
    onTermsClick: () -> Unit,
    onPolicyClick: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val termsText = privacyText()

    Scaffold(
        containerColor = MaterialTheme.extendedColorScheme.surface,
        topBar = {
            PersianTopAppBar(
                left = PersianTopAppBarLeft.Navigation(
                    onClick = onBackClick
                ),
                title = stringResource(R.string.auth_title)
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
                text = stringResource(id = R.string.auth_header),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.extendedColorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            PersianForm(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.large),
                content = PersianFormContent.Input(
                    value = state.email,
                    onValueChange = onEmailInput,
                    leadingIcon = painterResource(id = R.drawable.ic_alternate_email),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),

                    ),
                caption = PersianFormCaptionConfig(
                    text = "",
                    errorText = if (state.isEmailError && state.emailError != null)
                        stringResource(id = state.emailError)
                    else null
                ),
                enabled = !state.isLoading,
                isError = state.isEmailError,
                isValid = !state.isEmailError && state.email.isNotEmpty()
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            PersianPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.large),
                text = stringResource(R.string.send_code_label),
                onClick = {
                    onSendCodeClick()
                    keyboardController?.hide()
                },
                sizes = PersianButtonDefaults.largeSizes(),
                loading = state.isLoading
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
            ClickableText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.extraLarge),
                text = termsText,
                style = MaterialTheme.typography.bodyMedium,
                onClick = { offset ->
                    termsText.getStringAnnotations(offset, offset)
                        .firstOrNull()?.let { span ->
                            if (span.tag == "tnc") {
                                onTermsClick()
                            }
                            if (span.tag == "pp") {
                                onPolicyClick()
                            }
                        }
                }
            )
        }
    }
}


@Composable
fun privacyText(): AnnotatedString {
    val tnc = "tnc"
    val privacyPolicy = "pp"
    return buildAnnotatedString {
        withStyle(style = SpanStyle(color = MaterialTheme.extendedColorScheme.onSurfaceVariant)) {
            append(stringResource(id = R.string.agreement_start))
        }
        withStyle(style = SpanStyle(color = MaterialTheme.extendedColorScheme.primary)) {
            pushStringAnnotation(tag = tnc, annotation = tnc)
            append(stringResource(id = R.string.terms_of_service))
        }
        withStyle(style = SpanStyle(color = MaterialTheme.extendedColorScheme.onSurfaceVariant)) {
            append(stringResource(id = R.string._and_))
        }
        withStyle(style = SpanStyle(color = MaterialTheme.extendedColorScheme.primary)) {
            pushStringAnnotation(tag = privacyPolicy, annotation = privacyPolicy)
            append(stringResource(id = R.string.privacy_policy))
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SignInScreenPreview() {
    CoinCornTheme {
        Surface {
            AuthEnterEmailScreen(
                state = AuthEnterEmailScreenState(),
                onBackClick = {},
                onEmailInput = {},
                onSendCodeClick = {},
                onTermsClick = {},
                onPolicyClick = {}
            )
        }
    }
}