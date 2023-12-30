package ru.coincorn.app.main

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.coincorn.app.di.MainNavigation
import ru.coincorn.app.dataStore.Constants.INTRO
import ru.coincorn.app.dataStore.get
import ru.coincorn.app.navigation.AppNavigator
import ru.coincorn.app.navigation.Destination
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @MainNavigation appNavigator: AppNavigator,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    private val _uiState = MutableStateFlow(false)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { true }
            if (dataStore.get(INTRO) != true) {
                appNavigator.newRootScreen(Destination.Intro)
            }
            _uiState.update { false }
        }
    }
}