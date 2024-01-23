package ru.coincorn.app.featureVerification.data.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.coincorn.app.core.network.util.onError
import ru.coincorn.app.core.network.util.onSuccess
import ru.coincorn.app.featureVerification.data.api.VerificationApi
import ru.coincorn.app.featureVerification.data.request.VerifyRequestModel
import javax.inject.Inject

class VerificationRemoteDataSource @Inject constructor(
    private val api: VerificationApi,
) {

    suspend fun resendEmail(): Flow<Boolean> = flow {
        api.resendEmail()
            .onSuccess { _, _ ->
                emit(true)
            }
            .onError {
                throw it
            }
    }

    suspend fun verify(verifyRequestModel: VerifyRequestModel): Flow<Boolean> = flow {
        api.verify(verifyRequestModel)
            .onSuccess { _, _ ->
                emit(true)
            }
            .onError {
                throw it
            }
    }
}
