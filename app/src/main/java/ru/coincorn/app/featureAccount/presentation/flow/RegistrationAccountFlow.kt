package ru.coincorn.app.featureAccount.presentation.flow

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import ru.coincorn.app.core.navigation.Destination
import ru.coincorn.app.core.navigation.NavHost
import ru.coincorn.app.core.navigation.NavigationEffects
import ru.coincorn.app.core.navigation.composable
import ru.coincorn.app.featureAccount.presentation.finish.RegistrationAccountFinishRoute
import ru.coincorn.app.featureAccount.presentation.intro.RegistrationAccountIntroRoute
import ru.coincorn.app.featureAccount.presentation.registrationAccount.RegistrationAccountRoute

@Composable
fun RegistrationAccountFlow(
    registrationAccountFlowViewModel: RegistrationAccountFlowViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    NavigationEffects(
        navigationChannel = registrationAccountFlowViewModel.navigationChannel,
        navHostController = navController
    )
    NavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = navController,
        startDestination = Destination.RegistrationAccountIntro
    ) {
        composable(Destination.RegistrationAccountIntro) {
            RegistrationAccountIntroRoute()
        }
        composable(Destination.RegistrationAccount) {
            RegistrationAccountRoute()
        }
        composable(Destination.RegistrationAccountFinish) {
            RegistrationAccountFinishRoute()
        }
    }
}