package ru.coincorn.app.featureAuth.flow

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import ru.coincorn.app.featureAuth.welcome.WelcomeRoute
import ru.coincorn.app.navigation.Destination
import ru.coincorn.app.navigation.NavHost
import ru.coincorn.app.navigation.NavigationEffects
import ru.coincorn.app.navigation.composable

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
        composable(Destination.SignUp) {

        }
        composable(Destination.SignIn) {

        }
    }
}