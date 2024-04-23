package ru.coincorn.app.featureProfile.presentation.flow

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.coincorn.app.core.navigation.AppNavigator
import ru.coincorn.app.di.NestedNavigation
import javax.inject.Inject

@HiltViewModel
class RegistrationNameFlowViewModel @Inject constructor(
    @NestedNavigation private val nameNavigation: AppNavigator
) : ViewModel() {

    val navigationChannel = nameNavigation.navigationChannel
}