package ru.coincorn.app.main

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.coincorn.app.di.MainNavigation
import ru.coincorn.app.core.dataStore.Constants.INTRO
import ru.coincorn.app.core.dataStore.get
import ru.coincorn.app.featureAuth.data.response.AuthStep
import ru.coincorn.app.featureAuth.domain.repository.AuthRepository
import ru.coincorn.app.featureAuth.domain.repository.CredentialsRepository
import ru.coincorn.app.core.navigation.AppNavigator
import ru.coincorn.app.core.navigation.Destination
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @MainNavigation appNavigator: AppNavigator,
    private val dataStore: DataStore<Preferences>,
    private val credentialsRepository: CredentialsRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    private val _uiState = MutableStateFlow(false)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { true }
            if (!credentialsRepository.isLogin()) {
                if (dataStore.get(INTRO) != true) {
                    appNavigator.newRootScreen(Destination.Intro)
                } else {
                    appNavigator.newRootScreen(Destination.AuthFlow)
                }
            } else {
                authRepository
                    .fetchAuthStep()
                    .collectLatest {
                        when (it) {
                            AuthStep.ACCOUNT -> {}
                            AuthStep.CURRENCY -> {}
                            AuthStep.DONE -> {}
                            AuthStep.CONFIRM -> appNavigator.newRootScreen(Destination.RegistrationFlow, it.toString())
                        }
                    }
            }
            _uiState.update { false }
        }
    }
}