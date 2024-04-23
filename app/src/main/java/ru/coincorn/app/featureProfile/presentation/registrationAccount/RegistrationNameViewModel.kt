package ru.coincorn.app.featureProfile.presentation.registrationAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.coincorn.app.core.navigation.AppNavigator
import ru.coincorn.app.core.navigation.Destination
import ru.coincorn.app.di.MainNavigation
import ru.coincorn.app.featureProfile.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class RegistrationNameViewModel @Inject constructor(
    @MainNavigation private val nameNavigator: AppNavigator,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistrationNameScreenState())
    val uiState: StateFlow<RegistrationNameScreenState> = _uiState.asStateFlow()

    fun back() {
        viewModelScope.launch {
            nameNavigator.back()
        }
    }

    fun updateName(newValue: String) {
        _uiState.update { currentState ->
            currentState.copy(
                name = newValue
            )
        }
    }

    fun apply(){
        viewModelScope.launch {
            _uiState.update {currentState->
                currentState.copy(
                    isLoading = true
                )
            }
            userRepository
                .updateName(_uiState.value.name)
                .collectLatest {
                    nameNavigator.navigateTo(Destination.RegistrationAccountFlow)
                }
            _uiState.update {currentState->
                currentState.copy(
                    isLoading = false
                )
            }
        }
    }
}