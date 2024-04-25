package ru.coincorn.app.featureIntro.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.madmaximuus.persian.buttons.PersianButtonDefaults
import io.github.madmaximuus.persian.buttons.PersianTertiaryButton
import io.github.madmaximuus.persian.foundation.extendedColorScheme
import io.github.madmaximuus.persian.foundation.spacing
import io.github.madmaximuus.persian.pageIndicator.PersianPageIndicator
import ru.coincorn.app.R
import ru.coincorn.app.core.ui.components.GradientBackground

@Composable
fun IntroRoute(
    viewModel: IntroViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    IntroScreen(
        state = state,
        onPageChange = viewModel::updatePage,
        onSkipClick = viewModel::skip,
        onNextClick = viewModel::next
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun IntroScreen(
    state: IntroScreenState,
    onPageChange: (Int) -> Unit,
    onSkipClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val pagerState = rememberPagerState {
        state.pages.size
    }

    LaunchedEffect(state.currentPage) {
        pagerState.animateScrollToPage(state.currentPage)
    }

    LaunchedEffect(pagerState.currentPage) {
        onPageChange(pagerState.currentPage)
    }

    GradientBackground(
        modifier = Modifier.fillMaxSize(),
        gradientColors = listOf(
            0f to MaterialTheme.extendedColorScheme.primaryContainer,
            .5f to MaterialTheme.extendedColorScheme.primaryContainer,
            .75f to MaterialTheme.extendedColorScheme.surface
        ),
    ){
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
            color = Color.Transparent
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                HorizontalPager(
                    state = pagerState
                ) { page ->
                    IntoPage(model = state.pages[page])
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = MaterialTheme.spacing.extraExtraLarge),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PersianTertiaryButton(
                        modifier = Modifier.padding(start = MaterialTheme.spacing.large),
                        text = stringResource(id = R.string.skip),
                        sizes = PersianButtonDefaults.smallSizes(),
                        onClick = onSkipClick,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    PersianPageIndicator(
                        modifier = Modifier.wrapContentWidth(),
                        pagerState = pagerState
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    PersianTertiaryButton(
                        modifier = Modifier.padding(end = MaterialTheme.spacing.large),
                        text = stringResource(id = R.string.next),
                        sizes = PersianButtonDefaults.smallSizes(),
                        onClick = onNextClick
                    )
                }
            }
        }
    }
}