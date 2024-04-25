package ru.coincorn.app.featureProfile.presentation.registrationName

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
import io.github.madmaximuus.persian.foundation.extendedColorScheme
import io.github.madmaximuus.persian.foundation.spacing
import io.github.madmaximuus.persian.inputs.PersianOutlineInput
import io.github.madmaximuus.persian.topAppBar.PersianTopAppBar
import io.github.madmaximuus.persian.topAppBar.PersianTopAppBarLeft
import ru.coincorn.app.R
import ru.coincorn.app.core.ui.theme.CoinCornTheme

@Composable
fun RegistrationNameRoute(
    viewModel: RegistrationNameViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    RegistrationNameScreen(
        state = state,
        onBackClick = viewModel::back,
        onNameInput = viewModel::updateName,
        onApplyClick = viewModel::apply
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegistrationNameScreen(
    state: RegistrationNameScreenState,
    onBackClick: () -> Unit,
    onNameInput: (String) -> Unit,
    onApplyClick: () -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.extendedColorScheme.surface,
        topBar = {
            PersianTopAppBar(
                left = PersianTopAppBarLeft.Navigation(
                    customIcon = painterResource(id = R.drawable.ic_arrow_back),
                    onClick = onBackClick
                ),
                title = stringResource(R.string.registration_title),
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
                text = stringResource(id = R.string.registration_name_title),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.extendedColorScheme.onSurface
            )
            Text(
                modifier = Modifier.padding(
                    top = MaterialTheme.spacing.small,
                    start = MaterialTheme.spacing.large,
                    end = MaterialTheme.spacing.large
                ),
                text = stringResource(id = R.string.registration_name_text),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            PersianOutlineInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.large),
                value = state.name,
                onValueChange = onNameInput,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done
                ),
                enabled = !state.isLoading,
                leadingIcon = painterResource(id = R.drawable.ic_person_outlined),
            )
            Spacer(modifier = Modifier.weight(1f))
            PersianPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.large),
                text = stringResource(R.string.apply_button_label),
                onClick = onApplyClick,
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
            RegistrationNameScreen(
                state = RegistrationNameScreenState(),
                onBackClick = {},
                onNameInput = {},
                onApplyClick = {}
            )
        }
    }
}