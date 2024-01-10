package ru.coincorn.app.featureIntro.presentation

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.coincorn.app.core.dataStore.Constants.INTRO
import ru.coincorn.app.core.dataStore.save
import ru.coincorn.app.di.MainNavigation
import ru.coincorn.app.featureIntro.data.dataSource.IntroDataSource
import ru.coincorn.app.core.navigation.AppNavigator
import ru.coincorn.app.core.navigation.Destination
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    @MainNavigation private val appNavigator: AppNavigator,
    private val introDataSource: IntroDataSource,
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {

    private val _uiState = MutableStateFlow(IntroScreenState())
    val uiState: StateFlow<IntroScreenState> get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    pages = introDataSource.getIntroScreens()
                )
            }
            dataStore.save(INTRO, true)
        }
    }

    fun next() {
        viewModelScope.launch {
            if (_uiState.value.currentPage == _uiState.value.pages.size - 1)
                appNavigator.newRootScreen(Destination.AuthFlow)
            else {
                _uiState.update { currentState ->
                    currentState.copy(
                        currentPage = currentState.currentPage + 1
                    )
                }
            }
        }
    }

    fun skip() {
        viewModelScope.launch {
            appNavigator.newRootScreen(Destination.AuthFlow)
        }
    }

    fun updatePage(page: Int) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    currentPage = page
                )
            }
        }
    }
}