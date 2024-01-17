package ru.coincorn.app.core.error.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.coincorn.app.core.navigation.AppNavigator
import ru.coincorn.app.core.navigation.Destination
import ru.coincorn.app.di.MainNavigation
import javax.inject.Inject

@HiltViewModel
class AuthErrorViewModel @Inject constructor(
    @MainNavigation private val appNavigator: AppNavigator,
) : ViewModel() {

    fun goToAuth() {
        viewModelScope.launch {
            appNavigator.newRootScreen(Destination.AuthFlow)
        }
    }
}