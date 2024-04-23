package ru.coincorn.app.featureAccount.presentation.registrationAccount

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.madmaximuus.persian.buttons.PersianButtonDefaults
import io.github.madmaximuus.persian.buttons.PersianPrimaryButton
import io.github.madmaximuus.persian.forms.PersianForm
import io.github.madmaximuus.persian.forms.PersianFormCaptionConfig
import io.github.madmaximuus.persian.forms.PersianFormContent
import io.github.madmaximuus.persian.forms.PersianFormSubheadConfig
import io.github.madmaximuus.persian.foundation.extendedColorScheme
import io.github.madmaximuus.persian.foundation.spacing
import io.github.madmaximuus.persian.select.SelectActionItem
import io.github.madmaximuus.persian.topAppBar.PersianTopAppBar
import io.github.madmaximuus.persian.topAppBar.PersianTopAppBarLeft
import ru.coincorn.app.BuildConfig
import ru.coincorn.app.R
import ru.coincorn.app.core.ui.theme.CoinCornTheme

@Composable
fun RegistrationAccountRoute(
    viewModel: RegistrationAccountViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    RegistrationAccountScreen(
        state = state,
        onBackClick = viewModel::back,
        onAmountInput = viewModel::updateAmount,
        onSelectCurrency = viewModel::updateSelectedCurrency,
        onSelectAccountType = viewModel::updateSelectedType,
        onCreateClick = viewModel::createAccount
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegistrationAccountScreen(
    state: RegistrationAccountScreenState,
    onBackClick: () -> Unit,
    onAmountInput: (String) -> Unit,
    onSelectCurrency: (String, Int) -> Unit,
    onSelectAccountType: (String, Int) -> Unit,
    onCreateClick: () -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.extendedColorScheme.surface,
        topBar = {
            PersianTopAppBar(
                left = PersianTopAppBarLeft.Navigation(
                    customIcon = painterResource(id = R.drawable.ic_arrow_back),
                    onClick = onBackClick
                ),
                title = stringResource(R.string.main_account_title),
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
                text = stringResource(id = R.string.registration_account_header),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.extendedColorScheme.onSurface
            )
            Text(
                modifier = Modifier.padding(
                    top = MaterialTheme.spacing.small,
                    start = MaterialTheme.spacing.large,
                    end = MaterialTheme.spacing.large
                ),
                text = stringResource(id = R.string.registration_account_message),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            PersianForm(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.large),
                subhead = PersianFormSubheadConfig(
                    text = stringResource(R.string.input_amount_label)
                ),
                content = PersianFormContent.Input(
                    value = state.amount,
                    onValueChange = onAmountInput,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Done
                    ),
                    leadingIcon = painterResource(id = R.drawable.ic_savings_outlined),
                ),
                caption = PersianFormCaptionConfig(
                    text = "",
                ),
                enabled = !state.isLoading,
                isValid = state.amount.isNotEmpty()
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraExtraSmall))
            PersianForm(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.large),
                subhead = PersianFormSubheadConfig(
                    text = stringResource(R.string.input_currency_list)
                ),
                content = PersianFormContent.Select(
                    selected = state.selectedCurrency,
                    values = state.currencyListResponse.map {
                        SelectActionItem.IconUrl(
                            title = it.name,
                            iconUrl = "${BuildConfig.BASE_URL}/${it.icon}"
                        )
                    },
                    onSelectedChange = onSelectCurrency,
                    leadingIcon = painterResource(id = R.drawable.ic_currency_exchange)
                ),
                caption = PersianFormCaptionConfig(
                    text = "",
                ),
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraExtraSmall))
            PersianForm(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.large),
                subhead = PersianFormSubheadConfig(
                    text = stringResource(R.string.input_account_type)
                ),
                content = PersianFormContent.Select(
                    selected = state.selectedAccountType,
                    values = state.accountTypesResponse.map {
                        SelectActionItem.IconUrl(
                            title = it.name,
                            iconUrl = "${BuildConfig.BASE_URL}/${it.icon}"
                        )
                    },
                    onSelectedChange = onSelectAccountType,
                    leadingIcon = painterResource(id = R.drawable.ic_local_atm)
                ),
                caption = PersianFormCaptionConfig(
                    text = "",
                ),
            )
            Spacer(modifier = Modifier.weight(1f))
            PersianPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.large),
                text = stringResource(R.string.create_button_label),
                onClick = onCreateClick,
                sizes = PersianButtonDefaults.largeSizes(),
                loading = state.isLoading
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraExtraLarge))
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RegistrationAccountScreenPreview() {
    CoinCornTheme {
        Surface {
            RegistrationAccountScreen(
                state = RegistrationAccountScreenState(),
                onBackClick = {},
                onAmountInput = {},
                onSelectCurrency = { _, _ -> },
                onSelectAccountType = { _, _ -> },
                onCreateClick = {}
            )
        }
    }
}