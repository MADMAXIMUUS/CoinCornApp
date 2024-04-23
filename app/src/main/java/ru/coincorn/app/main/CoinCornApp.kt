package ru.coincorn.app.main

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
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.github.madmaximuus.persian.foundation.extendedColorScheme
import ru.coincorn.app.core.error.model.CommonErrorArgType
import ru.coincorn.app.core.error.presentation.auth.AuthErrorRoute
import ru.coincorn.app.core.error.presentation.common.CommonErrorRoute
import ru.coincorn.app.core.error.presentation.connection.ConnectionErrorRoute
import ru.coincorn.app.core.navigation.Destination
import ru.coincorn.app.core.navigation.NavHost
import ru.coincorn.app.core.navigation.NavigationEffects
import ru.coincorn.app.core.navigation.composable
import ru.coincorn.app.featureAccount.presentation.flow.RegistrationAccountFlow
import ru.coincorn.app.featureAuth.presentation.flow.AuthFlow
import ru.coincorn.app.featureIntro.presentation.IntroRoute
import ru.coincorn.app.featureProfile.presentation.flow.RegistrationNameFlow

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
            composable(Destination.Root) {}
            composable(
                destination = Destination.CommonError,
                arguments = listOf(
                    navArgument("error_model") {
                        type = CommonErrorArgType()
                    }
                )
            ) {
                CommonErrorRoute()
            }
            composable(Destination.ConnectionError) {
                ConnectionErrorRoute()
            }
            composable(Destination.AuthError) {
                AuthErrorRoute()
            }
            composable(Destination.Intro) {
                IntroRoute()
            }
            composable(Destination.AuthFlow) {
                AuthFlow()
            }
            composable(Destination.RegistrationNameFlow) {
                RegistrationNameFlow()
            }
            composable(Destination.RegistrationAccountFlow) {
                RegistrationAccountFlow()
            }
            composable(Destination.MainFlow) {

            }
        }
    }
}