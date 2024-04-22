package ru.coincorn.app.featureAuth.data.dataSource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.coincorn.app.core.network.util.onError
import ru.coincorn.app.core.network.util.onSuccess
import ru.coincorn.app.featureAuth.data.api.AuthApi
import ru.coincorn.app.featureAuth.data.request.AuthRequestModel
import ru.coincorn.app.featureAuth.data.request.CodeRequestModel
import ru.coincorn.app.featureAuth.data.response.AuthStep
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val api: AuthApi,
) {

    suspend fun signUp(authRequestModel: AuthRequestModel): Flow<Unit> = flow {
        api.signUp(authRequestModel)
            .onSuccess { _, _ ->
                emit(Unit)
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

    suspend fun resendEmail(): Flow<Unit> = flow {
        api.resendEmail()
            .onSuccess { _, _ ->
                emit(Unit)
            }
            .onError {
                throw it
            }
    }

    suspend fun verify(verifyRequestModel: CodeRequestModel): Flow<String> = flow {
        api.verify(verifyRequestModel)
            .onSuccess { _, headers ->
                headers["session_id"]?.let { emit(it) }
            }
            .onError {
                throw it
            }
    }
}
