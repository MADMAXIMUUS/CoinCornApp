package ru.coincorn.app.core.error.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.madmaximuus.persian.buttons.PersianButtonDefaults
import io.github.madmaximuus.persian.buttons.PersianPrimaryButton
import io.github.madmaximuus.persian.foundation.extendedColorScheme
import io.github.madmaximuus.persian.foundation.spacing
import ru.coincorn.app.R
import ru.coincorn.app.core.ui.theme.CoinCornTheme

@Composable
fun CommonErrorRoute(
    viewModel: CommonErrorViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    CommonErrorScreen(
        state = state,
        onBackClick = viewModel::back
    )
}

@Composable
private fun CommonErrorScreen(
    state: CommonErrorScreenState,
    onBackClick: () -> Unit
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
                modifier = Modifier
                    .size(320.dp, 411.dp),
                painter = painterResource(id = R.drawable.ic_common_error),
                contentDescription = "",
                colorFilter = ColorFilter.tint(MaterialTheme.extendedColorScheme.error)
            )
            Spacer(modifier = Modifier.weight(.5f))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = state.title,
                color = MaterialTheme.extendedColorScheme.primary,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = state.text,
                color = MaterialTheme.extendedColorScheme.secondary,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            PersianPrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.try_again),
                sizes = PersianButtonDefaults.largeSizes(),
                onClick = onBackClick
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraExtraLarge))
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CommonErrorScreenPreview() {
    CoinCornTheme {
        Surface {
            CommonErrorScreen(
                state = CommonErrorScreenState(
                    title = "Some title",
                    text = "Some text"
                ),
                onBackClick = {}
            )
        }
    }
}