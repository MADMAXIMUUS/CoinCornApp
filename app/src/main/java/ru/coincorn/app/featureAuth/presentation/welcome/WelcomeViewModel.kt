package ru.coincorn.app.featureAuth.presentation.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.coincorn.app.core.navigation.AppNavigator
import ru.coincorn.app.core.navigation.Destination
import ru.coincorn.app.di.NestedNavigation
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    @NestedNavigation private val appNavigator: AppNavigator
) : ViewModel() {

    fun signUp() {
        viewModelScope.launch {
            appNavigator.navigateTo(Destination.SignUp)
        }
    }

    fun signIn() {
        viewModelScope.launch {
            appNavigator.navigateTo(Destination.SignIn)
        }
    }
}