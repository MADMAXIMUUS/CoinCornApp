package ru.coincorn.app.featureAuth.presentation.flow

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.coincorn.app.core.navigation.Destination
import ru.coincorn.app.core.navigation.NavHost
import ru.coincorn.app.core.navigation.NavigationEffects
import ru.coincorn.app.core.navigation.composable
import ru.coincorn.app.featureAuth.presentation.authEnterCode.AuthEnterCodeRoute
import ru.coincorn.app.featureAuth.presentation.authEnterEmail.AuthEnterEmailRoute
import ru.coincorn.app.featureAuth.presentation.welcome.WelcomeRoute

@Composable
fun AuthFlow(
    viewModel: AuthFlowViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    NavigationEffects(
        navigationChannel = viewModel.navigationChannel,
        navHostController = navController
    )
    NavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = navController,
        startDestination = Destination.Welcome,
    ) {
        composable(Destination.Welcome) {
            WelcomeRoute()
        }
        composable(Destination.AuthEnterEmail) {
            AuthEnterEmailRoute()
        }
        composable(
            destination = Destination.AuthEnterCode,
            arguments = listOf(
                navArgument("email_param") {
                    type = NavType.StringType
                }
            )
        ) {
            AuthEnterCodeRoute()
        }
    }
}