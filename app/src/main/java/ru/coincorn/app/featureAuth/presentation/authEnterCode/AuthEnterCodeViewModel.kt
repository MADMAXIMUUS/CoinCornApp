package ru.coincorn.app.featureAuth.presentation.authEnterCode

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.CountDownTimer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.coincorn.app.core.navigation.AppNavigator
import ru.coincorn.app.core.navigation.Destination
import ru.coincorn.app.core.notification.PushEvent
import ru.coincorn.app.core.notification.PushEventBus
import ru.coincorn.app.di.MainNavigation
import ru.coincorn.app.featureAuth.data.response.AuthStep
import ru.coincorn.app.featureAuth.domain.repository.AuthRepository
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AuthEnterCodeViewModel @Inject constructor(
    @MainNavigation private val appNavigation: AppNavigator,
    private val authRepository: AuthRepository,
    private val pushEventBus: PushEventBus,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthEnterCodeScreenState())
    val uiState: StateFlow<AuthEnterCodeScreenState> = _uiState.asStateFlow()

    private val timer = object : CountDownTimer(5 * 60 * 1000, 1000) {

        @SuppressLint("SimpleDateFormat")
        override fun onTick(millisUntilFinished: Long) {
            val formatter = SimpleDateFormat("mm:ss")
            val timeString = formatter.format(Date(millisUntilFinished))
            _uiState.update { currentState ->
                currentState.copy(
                    timer = timeString
                )
            }
        }

        override fun onFinish() {
            _uiState.update { currentState ->
                currentState.copy(
                    timer = "",
                    resendEnabled = true
                )
            }
        }
    }

    init {
        viewModelScope.launch {
            val email = savedStateHandle.get<String>("email_param") ?: ""
            _uiState.update { currentState ->
                currentState.copy(
                    email = email
                )
            }
            timer.start()
            pushEventBus.events.collectLatest { pushEvent ->
                when (pushEvent){
                    is PushEvent.AuthLoginCode -> {
                        val code = pushEvent.loginCode
                        _uiState.update { currentState ->
                            currentState.copy(
                                code = code.map { it.toString() }
                            )
                        }
                        verify()
                    }
                    else -> {}
                }
            }
        }
    }

    fun updateCode(value: String, index: Int) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                val newCode = mutableListOf<String>()
                newCode.addAll(currentState.code)
                newCode[index] = value
                currentState.copy(
                    code = newCode
                )
            }
            if (_uiState.value.code.all { it.isNotEmpty() }) {
                verify()
            }
        }
    }

    fun resendEmail() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { currentState ->
                currentState.copy(
                    resendLoading = true
                )
            }
            authRepository.resendEmail()
            _uiState.update { currentState ->
                currentState.copy(
                    resendLoading = false,
                    resendEnabled = false
                )
            }
            timer.start()
        }
    }

    private fun verify() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { currentState ->
                currentState.copy(
                    verifyLoading = true
                )
            }
            var code = ""
            _uiState.value.code.forEach {
                code += it
            }
            authRepository
                .verify(
                    code,
                    _uiState.value.email
                )
                .collectLatest {
                    authRepository.fetchAuthStep()
                        .collectLatest { authStep ->
                            when (authStep) {
                                AuthStep.DONE -> appNavigation.newRootScreen(Destination.MainFlow)
                                AuthStep.NAME -> appNavigation.newRootScreen(Destination.RegistrationNameFlow)
                                AuthStep.ACCOUNT ->
                                    appNavigation.newRootScreen(Destination.RegistrationAccountFlow)
                            }
                        }
                }
            _uiState.update { currentState ->
                currentState.copy(
                    verifyLoading = false
                )
            }
        }
    }
}