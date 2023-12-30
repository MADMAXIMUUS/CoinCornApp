package ru.coincorn.app.featureAuth.flow

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.coincorn.app.di.NestedNavigation
import ru.coincorn.app.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class AuthFlowViewModel @Inject constructor(
    @NestedNavigation private val appNavigator: AppNavigator
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel
}