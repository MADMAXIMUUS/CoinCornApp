package ru.coincorn.app.featureAccount.presentation.flow

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.coincorn.app.core.navigation.AppNavigator
import ru.coincorn.app.di.NestedNavigation
import javax.inject.Inject

@HiltViewModel
class RegistrationAccountFlowViewModel @Inject constructor(
    @NestedNavigation private val accountNavigation: AppNavigator
) : ViewModel() {

    val navigationChannel = accountNavigation.navigationChannel
}