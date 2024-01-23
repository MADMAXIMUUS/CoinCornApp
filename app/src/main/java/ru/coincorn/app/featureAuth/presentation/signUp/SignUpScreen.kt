package ru.coincorn.app.featureAuth.presentation.signUp

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.madmaximuus.persian.buttons.PersianButtonDefaults
import io.github.madmaximuus.persian.buttons.PersianPrimaryButton
import io.github.madmaximuus.persian.buttons.PersianSecondaryButton
import io.github.madmaximuus.persian.dividers.PersianFullWidthHorizontalDivider
import io.github.madmaximuus.persian.forms.PersianForm
import io.github.madmaximuus.persian.forms.PersianFormCaptionConfig
import io.github.madmaximuus.persian.forms.PersianFormContent
import io.github.madmaximuus.persian.forms.PersianFormSubheadConfig
import io.github.madmaximuus.persian.foundation.extendedColorScheme
import io.github.madmaximuus.persian.foundation.spacing
import io.github.madmaximuus.persian.inputs.InputsTransformations
import io.github.madmaximuus.persian.topAppBar.PersianTopAppBar
import io.github.madmaximuus.persian.topAppBar.PersianTopAppBarLeft
import io.github.madmaximuus.persian.topAppBar.PersianTopAppBarMiddle
import ru.coincorn.app.R
import ru.coincorn.app.core.ui.theme.CoinCornTheme

@Composable
fun SignUpRoute(
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    SignUpScreen(
        state = state,
        onBackClick = viewModel::back,
        onNameInput = viewModel::updateName,
        onEmailInput = viewModel::updateEmail,
        onPasswordInput = viewModel::updatePassword,
        onPasswordVisibilityChange = viewModel::updatePasswordVisibility,
        onTermsClick = {},
        onPolicyClick = {},
        onSignUpClick = viewModel::signUp,
        onContinueWithClick = {},
        onGoToSignIn = viewModel::goToSignIn
    )
}

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
private fun SignUpScreen(
    state: SignUpScreenState,
    onBackClick: () -> Unit,
    onNameInput: (String) -> Unit,
    onEmailInput: (String) -> Unit,
    onPasswordInput: (String) -> Unit,
    onPasswordVisibilityChange: () -> Unit,
    onTermsClick: () -> Unit,
    onPolicyClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onContinueWithClick: () -> Unit,
    onGoToSignIn: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val termsText = privacyText()
    val signInText = signInText()

    Scaffold(
        containerColor = MaterialTheme.extendedColorScheme.surface,
        topBar = {
            PersianTopAppBar(
                left = PersianTopAppBarLeft.Navigation(
                    customIcon = painterResource(id = R.drawable.ic_arrow_back),
                    onClick = onBackClick
                ),
                middle = PersianTopAppBarMiddle.Title(
                    text = stringResource(R.string.sign_up_title)
                ),
                actionItemsCount = 0
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            PersianForm(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.large),
                subhead = PersianFormSubheadConfig(
                    text = stringResource(R.string.input_name_label)
                ),
                content = PersianFormContent.Input(
                    value = state.name,
                    onValueChange = onNameInput,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    leadingIcon = painterResource(id = R.drawable.ic_person_outlined),
                ),
                caption = PersianFormCaptionConfig(
                    text = "",
                    errorText = if (state.isNameError && state.nameError != null)
                        stringResource(id = state.nameError)
                    else null
                ),
                enabled = !state.isLoading,
                isError = state.isNameError,
                isSuccess = !state.isNameError && state.name.isNotEmpty()
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            PersianForm(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.large),
                subhead = PersianFormSubheadConfig(
                    text = stringResource(R.string.input_email_label)
                ),
                content = PersianFormContent.Input(
                    value = state.email,
                    onValueChange = onEmailInput,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    leadingIcon = painterResource(id = R.drawable.ic_alternate_email),
                ),
                caption = PersianFormCaptionConfig(
                    text = "",
                    errorText = if (state.isEmailError && state.emailError != null)
                        stringResource(id = state.emailError)
                    else null
                ),
                enabled = !state.isLoading,
                isError = state.isEmailError,
                isSuccess = !state.isEmailError && state.email.isNotEmpty()
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            PersianForm(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.large),
                subhead = PersianFormSubheadConfig(
                    text = stringResource(R.string.input_password_label)
                ),
                content = PersianFormContent.Input(
                    value = state.password,
                    onValueChange = onPasswordInput,
                    leadingIcon = painterResource(id = R.drawable.ic_lock),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                    ),
                    trailingIcon = if (state.isPasswordVisible) painterResource(id = R.drawable.ic_visibility_off)
                    else painterResource(id = R.drawable.ic_visibility),
                    transformation = if (state.isPasswordVisible) InputsTransformations.none
                    else InputsTransformations.password,
                    onTrailingIconClick = onPasswordVisibilityChange
                ),
                caption = PersianFormCaptionConfig(
                    text = "",
                    errorText = if (state.isPasswordError && state.passwordError != null)
                        stringResource(id = state.passwordError)
                    else null
                ),
                enabled = !state.isLoading,
                isError = state.isPasswordError,
                isSuccess = !state.isPasswordError && state.password.isNotEmpty()
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
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
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            PersianPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.large),
                text = stringResource(R.string.sign_up_button_label),
                onClick = {
                    onSignUpClick()
                    keyboardController?.hide()
                },
                sizes = PersianButtonDefaults.largeSizes(state.isLoading),
                loading = state.isLoading
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
            PersianSecondaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.large),
                text = stringResource(R.string.continue_with_button_label),
                onClick = onContinueWithClick,
                enabled = !state.isLoading,
                sizes = PersianButtonDefaults.largeSizes(),
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            ClickableText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.large),
                text = signInText,
                style = MaterialTheme.typography.bodyMedium,
                onClick = { offset ->
                    signInText.getStringAnnotations(offset, offset)
                        .firstOrNull()?.let { span ->
                            if (span.tag == "signIn") {
                                onGoToSignIn()
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

@Composable
fun signInText(): AnnotatedString {
    val tag = "signIn"
    val textPart1 = stringResource(id = R.string.already_have_account)
    val textPart2 = stringResource(id = R.string.sign_in)
    val color = MaterialTheme.extendedColorScheme.onSurface
    val clickColor = MaterialTheme.extendedColorScheme.primary
    return buildAnnotatedString {
        withStyle(style = ParagraphStyle(textAlign = TextAlign.Center)) {
            withStyle(style = SpanStyle(color = color)) {
                append(textPart1)
            }
            withStyle(style = SpanStyle(color = clickColor)) {
                pushStringAnnotation(tag = tag, annotation = tag)
                append(textPart2)
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SignUpScreenPreview() {
    CoinCornTheme {
        Surface {
            SignUpScreen(
                state = SignUpScreenState(),
                onBackClick = {},
                onNameInput = {},
                onEmailInput = {},
                onPasswordInput = {},
                onPasswordVisibilityChange = {},
                onSignUpClick = {},
                onContinueWithClick = {},
                onGoToSignIn = {},
                onPolicyClick = {},
                onTermsClick = {}
            )
        }
    }
}