package ru.coincorn.app.core.error.presentation.common

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.coincorn.app.di.MainNavigation
import ru.coincorn.app.core.error.model.CommonErrorModel
import ru.coincorn.app.core.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class CommonErrorViewModel @Inject constructor(
    @MainNavigation private val appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(CommonErrorScreenState())
    val uiState: StateFlow<CommonErrorScreenState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val model = Gson().fromJson(
                savedStateHandle.get<String>("error_model"),
                CommonErrorModel::class.java
            )
            _uiState.update { currentState ->
                currentState.copy(
                    text = model?.message ?: ""
                )
            }
        }
    }

    fun back() {
        viewModelScope.launch {
            appNavigator.back()
        }
    }
}