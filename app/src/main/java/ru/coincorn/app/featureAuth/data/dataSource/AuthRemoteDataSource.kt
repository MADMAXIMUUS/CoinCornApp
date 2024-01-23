package ru.coincorn.app.featureAuth.data.dataSource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.coincorn.app.core.network.util.onError
import ru.coincorn.app.core.network.util.onSuccess
import ru.coincorn.app.featureAuth.data.api.AuthApi
import ru.coincorn.app.featureAuth.data.request.SignInRequestModel
import ru.coincorn.app.featureAuth.data.request.SignUpRequestModel
import ru.coincorn.app.featureAuth.data.response.AuthStep
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val api: AuthApi,
) {

    suspend fun signUp(signUpRequestModel: SignUpRequestModel): Flow<String> = flow {
        api.signUp(signUpRequestModel)
            .onSuccess { _, headers ->
                headers["session_id"]?.let { emit(it) }
            }
            .onError {
                throw it
            }
    }

    suspend fun signIn(signInRequestModel: SignInRequestModel): Flow<String> = flow {
        api.signIn(signInRequestModel)
            .onSuccess { _, headers ->
                headers["session_id"]?.let { emit(it) }
            }
            .onError {
                throw it
            }
    }

    suspend fun fetchStep(): Flow<AuthStep> = flow {
        api.fetchStep()
            .onSuccess { body, _ ->
                body?.step?.let { emit(it) }
            }
            .onError {
                throw it
            }
    }
}
