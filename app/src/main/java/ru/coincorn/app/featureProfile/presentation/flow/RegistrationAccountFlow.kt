package ru.coincorn.app.featureProfile.presentation.flow

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import ru.coincorn.app.core.navigation.Destination
import ru.coincorn.app.core.navigation.NavHost
import ru.coincorn.app.core.navigation.NavigationEffects
import ru.coincorn.app.core.navigation.composable
import ru.coincorn.app.featureProfile.presentation.registrationName.RegistrationNameRoute

@Composable
fun RegistrationNameFlow(
    registrationNameFlowViewModel: RegistrationNameFlowViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    NavigationEffects(
        navigationChannel = registrationNameFlowViewModel.navigationChannel,
        navHostController = navController
    )
    NavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = navController,
        startDestination = Destination.RegistrationName
    ) {
        composable(Destination.RegistrationName) {
            RegistrationNameRoute()
        }
    }
}