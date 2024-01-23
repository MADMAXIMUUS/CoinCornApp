package ru.coincorn.app.featureAccount.presentation.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.coincorn.app.core.navigation.AppNavigator
import ru.coincorn.app.core.navigation.Destination
import ru.coincorn.app.di.NestedNavigation
import javax.inject.Inject

@HiltViewModel
class RegistrationAccountIntroViewModel @Inject constructor(
    @NestedNavigation private val accountNavigator: AppNavigator,
) : ViewModel() {

    fun onNext() {
        viewModelScope.launch {
            accountNavigator.newRootScreen(Destination.RegistrationAccount)
        }
    }
}