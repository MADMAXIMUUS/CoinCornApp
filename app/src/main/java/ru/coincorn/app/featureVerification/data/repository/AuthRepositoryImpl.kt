package ru.coincorn.app.featureVerification.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import ru.coincorn.app.core.error.ErrorHandler
import ru.coincorn.app.featureVerification.data.datasource.VerificationRemoteDataSource
import ru.coincorn.app.featureVerification.data.request.VerifyRequestModel
import ru.coincorn.app.featureVerification.domain.repository.VerificationRepository
import javax.inject.Inject

class VerificationRepositoryImpl @Inject constructor(
    private val verificationRemoteSource: VerificationRemoteDataSource,
    private val errorHandler: ErrorHandler
) : VerificationRepository {

    override suspend fun resendEmail(): Flow<Boolean> {
        return verificationRemoteSource
            .resendEmail()
            .catch { e ->
                errorHandler.proceed(e)
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun verify(code: String): Flow<Boolean> {
        val verifyRequestModel = VerifyRequestModel(code)
        return verificationRemoteSource
            .verify(verifyRequestModel)
            .catch { e ->
                errorHandler.proceed(e)
            }
            .flowOn(Dispatchers.IO)
    }
}