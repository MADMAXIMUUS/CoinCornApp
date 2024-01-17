package ru.coincorn.app.core.error

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.coincorn.app.core.error.model.CommonErrorModel
import ru.coincorn.app.core.navigation.AppNavigator
import ru.coincorn.app.core.navigation.Destination
import ru.coincorn.app.di.MainNavigation
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ErrorResolver @Inject constructor(
    @MainNavigation private val appNavigator: AppNavigator,
    private val coroutineContext: CoroutineContext
) {

    fun resolveCommonHttpError(errorModel: CommonErrorModel) {
        CoroutineScope(coroutineContext).launch {
            appNavigator.navigateTo(Destination.CommonError, errorModel.toString())
        }
    }

    fun resolveConnectionError(){
        CoroutineScope(coroutineContext).launch {
            appNavigator.navigateTo(Destination.ConnectionError)
        }
    }

    fun resolveAuthError(){
        CoroutineScope(coroutineContext).launch {
            appNavigator.navigateTo(Destination.AuthError)
        }
    }
}