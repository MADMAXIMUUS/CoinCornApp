package ru.coincorn.app.main

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.coincorn.app.navigation.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import io.github.madmaximuus.persian.foundation.extendedColorScheme
import ru.coincorn.app.featureAuth.flow.AuthFlow
import ru.coincorn.app.featureIntro.presentation.IntroRoute
import ru.coincorn.app.navigation.Destination
import ru.coincorn.app.navigation.NavigationEffects
import ru.coincorn.app.navigation.composable

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinCornApp(
    mainViewModel: MainViewModel
) {
    val navController = rememberNavController()
    NavigationEffects(
        navigationChannel = mainViewModel.navigationChannel,
        navHostController = navController
    )
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        containerColor = MaterialTheme.extendedColorScheme.surface,
    ) { padding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                ),
            navController = navController,
            startDestination = Destination.Root
        ) {
            composable(Destination.Root) {

            }
            composable(Destination.Intro) {
                IntroRoute()
            }
            composable(Destination.AuthFlow) {
                AuthFlow()
            }
        }
    }
}