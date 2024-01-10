package ru.coincorn.app.core.error.presentation.connection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.coincorn.app.di.MainNavigation
import ru.coincorn.app.core.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class ConnectionErrorViewModel @Inject constructor(
    @MainNavigation private val appNavigator: AppNavigator,
) : ViewModel() {

    fun back() {
        viewModelScope.launch {
            appNavigator.back()
        }
    }
}