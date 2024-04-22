package ru.coincorn.app.featureAuth.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.coincorn.app.core.error.ErrorHandler
import ru.coincorn.app.core.notification.FcmRepository
import ru.coincorn.app.featureAuth.data.dataSource.AuthRemoteDataSource
import ru.coincorn.app.featureAuth.data.request.AuthRequestModel
import ru.coincorn.app.featureAuth.data.request.CodeRequestModel
import ru.coincorn.app.featureAuth.data.response.AuthStep
import ru.coincorn.app.featureAuth.domain.repository.AuthRepository
import ru.coincorn.app.featureAuth.domain.repository.CredentialsRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteSource: AuthRemoteDataSource,
    private val credentialsRepository: CredentialsRepository,
    private val fcmRepository: FcmRepository,
    private val errorHandler: ErrorHandler
) : AuthRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun auth(
        email: String
    ): Flow<Unit> {
        return fcmRepository.getDeviceToken()
            .catch { e ->
                errorHandler.proceed(e)
            }
            .flowOn(Dispatchers.IO)
            .flatMapMerge { token ->
                val signUpModel = AuthRequestModel(
                    email = email,
                    fcmPushToken = token
                )

                authRemoteSource
                    .signUp(signUpModel)
                    .catch { e ->
                        errorHandler.proceed(e)
                    }
                    .flowOn(Dispatchers.IO)
            }
    }

    override suspend fun resendEmail(): Flow<Unit> {
        return authRemoteSource
            .resendEmail()
            .catch { e ->
                errorHandler.proceed(e)
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun verify(
        code: String,
        email: String
    ): Flow<Unit> {
        val verifyRequestModel = CodeRequestModel(code = code, email = email)
        return authRemoteSource
            .verify(verifyRequestModel)
            .catch { e ->
                errorHandler.proceed(e)
            }
            .flowOn(Dispatchers.IO)
            .map { sessionId ->
                credentialsRepository.saveSessionId(sessionId)
            }
    }

    override suspend fun fetchAuthStep(): Flow<AuthStep> = authRemoteSource
        .fetchStep()
        .catch { e ->
            errorHandler.proceed(e)
        }
        .flowOn(Dispatchers.IO)


    override suspend fun signOut() {
        try {
            credentialsRepository.deleteSessionId()
            //emit(true)
        } catch (e: Exception) {
            errorHandler.proceed(e)
        }
    }
}