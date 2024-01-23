package ru.coincorn.app.featureAccount.presentation.finish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.coincorn.app.core.navigation.AppNavigator
import ru.coincorn.app.core.navigation.Destination
import ru.coincorn.app.di.MainNavigation
import javax.inject.Inject

@HiltViewModel
class RegistrationAccountFinishViewModel @Inject constructor(
    @MainNavigation private val appNavigator: AppNavigator,
) : ViewModel() {

    fun onNext() {
        viewModelScope.launch {
            appNavigator.newRootScreen(Destination.MainFlow)
        }
    }
}