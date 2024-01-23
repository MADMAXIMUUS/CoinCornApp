package ru.coincorn.app.featureVerification.presentation.verify

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.CountDownTimer
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
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
import ru.coincorn.app.core.dataStore.Constants.EMAIL
import ru.coincorn.app.core.dataStore.get
import ru.coincorn.app.core.dataStore.remove
import ru.coincorn.app.core.navigation.AppNavigator
import ru.coincorn.app.core.navigation.Destination
import ru.coincorn.app.di.MainNavigation
import ru.coincorn.app.featureVerification.domain.repository.VerificationRepository
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class VerifyViewModel @Inject constructor(
    @MainNavigation private val appNavigation: AppNavigator,
    private val verificationRepository: VerificationRepository,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _uiState = MutableStateFlow(VerifyScreenState())
    val uiState: StateFlow<VerifyScreenState> = _uiState.asStateFlow()

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
            val email = dataStore.get(EMAIL) ?: ""
            _uiState.update { currentState ->
                currentState.copy(
                    email = email
                )
            }
            timer.start()
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
        }
    }

    fun resendEmail() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { currentState ->
                currentState.copy(
                    resendLoading = true
                )
            }
            verificationRepository.resendEmail()
            _uiState.update { currentState ->
                currentState.copy(
                    resendLoading = false,
                    resendEnabled = false
                )
            }
            timer.start()
        }
    }

    fun verify() {
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
            verificationRepository
                .verify(code)
                .collectLatest {
                    if (it) {
                        dataStore.remove(EMAIL)
                        appNavigation.newRootScreen(Destination.RegistrationAccountFlow)
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